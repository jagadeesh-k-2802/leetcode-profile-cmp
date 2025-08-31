package com.jackappsdev.leetcode.presentation.screens.friends_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackappsdev.leetcode.constants.Constants.EMPTY_STRING
import com.jackappsdev.leetcode.domain.model.Friend
import com.jackappsdev.leetcode.presentation.base.EventDrivenViewModel
import com.jackappsdev.leetcode.presentation.screens.friends_page.event.FriendsEffect
import com.jackappsdev.leetcode.presentation.screens.friends_page.event.FriendsEvent
import com.jackappsdev.leetcode.domain.repository.FriendsRepository
import com.jackappsdev.leetcode.domain.usecase.AddFriendUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
class FriendsViewModel(
    private val friendsRepository: FriendsRepository,
    private val addFriend: AddFriendUseCase
): ViewModel(), EventDrivenViewModel<FriendsState, FriendsEvent, FriendsEffect> {

    private val _state = MutableStateFlow(FriendsState())
    override val state: StateFlow<FriendsState> = _state.asStateFlow()

    private val _effect = Channel<FriendsEffect>()
    override val effectFlow = _effect.receiveAsFlow()

    init {
        onInit()
    }

    private fun onInit() {
        viewModelScope.launch {
            friendsRepository.friends.collect { list ->
                _state.value = _state.value.copy(data = list, isLoading = false, error = false)
            }
        }
    }

    private fun onFriendItemClick(friend: Friend): FriendsEffect {
        return FriendsEffect.NavigateToFriendDetail(friend.username)
    }

    private fun onAddClick(): FriendsEffect {
        return FriendsEffect.ToggleAddSheetVisibility
    }

    private fun onAddUsernameChange(username: String) {
        _state.value = _state.value.copy(
            addUsername = username,
            addError = null,
            canSubmitData = username.isNotBlank()
        )
    }

    private suspend fun onSubmitAdd(): FriendsEffect? {
        val username = _state.value.addUsername.trim()
        _state.value = _state.value.copy(isSubmitInProgress = true, addError = null)
        val res = addFriend(username)
        var effect: FriendsEffect? = null

        res.fold(
            onSuccess = {
                effect = FriendsEffect.ToggleAddSheetVisibility
                _state.value = _state.value.copy(
                    isSubmitInProgress = false,
                    addUsername = EMPTY_STRING,
                    addError = null,
                    canSubmitData = true,
                )
            },
            onFailure = { error ->
                error.printStackTrace()
                _state.value = _state.value.copy(
                    isSubmitInProgress = false,
                    addError = error.message,
                    canSubmitData = true
                )
            }
        )

        return effect
    }

    override fun onEvent(event: FriendsEvent) {
        viewModelScope.launch {
            val effect = when (event) {
                is FriendsEvent.OnRefresh -> onInit()
                is FriendsEvent.OnFriendItemClick -> onFriendItemClick(event.friend)
                is FriendsEvent.OnAddClick -> onAddClick()
                is FriendsEvent.OnAddUsernameChange -> onAddUsernameChange(event.username)
                is FriendsEvent.OnSubmitAdd -> onSubmitAdd()
            }

            if (effect is FriendsEffect) {
                _effect.send(effect)
            }
        }
    }
}
