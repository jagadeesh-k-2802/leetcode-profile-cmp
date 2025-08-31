package com.jackappsdev.leetcode.presentation.screens.friend_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackappsdev.leetcode.domain.model.Friend
import com.jackappsdev.leetcode.domain.repository.FriendsRepository
import com.jackappsdev.leetcode.domain.usecase.GetCombinedProfileConfig
import com.jackappsdev.leetcode.domain.usecase.GetCombinedProfileUseCase
import com.jackappsdev.leetcode.presentation.base.EventDrivenViewModel
import com.jackappsdev.leetcode.presentation.screens.friend_detail.event.FriendDetailEffect
import com.jackappsdev.leetcode.presentation.screens.friend_detail.event.FriendDetailEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
class FriendDetailViewModel(
    @InjectedParam private val username: String,
    private val getCombinedProfile: GetCombinedProfileUseCase,
    private val friendsRepository: FriendsRepository,
) : ViewModel(), EventDrivenViewModel<FriendDetailState, FriendDetailEvent, FriendDetailEffect> {

    private val _state = MutableStateFlow(FriendDetailState())
    override val state: StateFlow<FriendDetailState> = _state.asStateFlow()

    private val _effect = Channel<FriendDetailEffect>()
    override val effectFlow = _effect.receiveAsFlow()

    init {
        onInit()
    }

    fun onInit() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, isError = false)
            getCombinedProfile(
                username = username,
                config = GetCombinedProfileConfig(includeBadges = true)
            ).collect { res ->
                res.onSuccess { profile ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isError = false,
                        data = profile
                    )
                    friendsRepository.addOrUpdate(
                        Friend(
                            username = profile.username,
                            ranking = profile.ranking,
                            problemsSolved = profile.totalSolved,
                            avatarUrl = profile.avatarUrl
                        )
                    )
                }.onFailure {
                    _state.value = _state.value.copy(isLoading = false, isError = true)
                }
            }
        }
    }

    private fun onBack(): FriendDetailEffect {
        if (state.value.deleteDialogVisible) { onToggleDeleteDialogVisible() }
        return FriendDetailEffect.GoBack
    }

    private fun onToggleDeleteDialogVisible() {
        _state.value = _state.value.copy(deleteDialogVisible = !state.value.deleteDialogVisible)
    }

    private suspend fun onDeleteFriend(): FriendDetailEffect {
        friendsRepository.remove(username)
        return FriendDetailEffect.GoBack
    }

    override fun onEvent(event: FriendDetailEvent) {
        viewModelScope.launch {
            val effect = when (event) {
                is FriendDetailEvent.OnRefresh -> onInit()
                is FriendDetailEvent.OnBack -> onBack()
                is FriendDetailEvent.OnToggleDeleteDialogVisible -> onToggleDeleteDialogVisible()
                is FriendDetailEvent.OnDeleteFriend -> onDeleteFriend()
            }

            if (effect is FriendDetailEffect) {
                _effect.send(effect)
            }
        }
    }
}
