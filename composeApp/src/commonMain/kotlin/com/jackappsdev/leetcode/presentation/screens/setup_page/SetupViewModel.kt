package com.jackappsdev.leetcode.presentation.screens.setup_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackappsdev.leetcode.domain.model.User
import com.jackappsdev.leetcode.domain.repository.UserRepository
import com.jackappsdev.leetcode.presentation.base.EventDrivenViewModel
import com.jackappsdev.leetcode.presentation.screens.setup_page.event.SetupEffect
import com.jackappsdev.leetcode.presentation.screens.setup_page.event.SetupEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
class SetupViewModel(
    private val userRepository: UserRepository
) : ViewModel(), EventDrivenViewModel<SetupState, SetupEvent, SetupEffect> {

    private val _state = MutableStateFlow(SetupState())
    override val state: StateFlow<SetupState> = _state.asStateFlow()

    private val _effect = Channel<SetupEffect>()
    override val effectFlow = _effect.receiveAsFlow()

    init {
        onInit()
    }

    private fun onInit() {
        viewModelScope.launch {
            userRepository.getUser().collect { user ->
                if (user != null) {
                    _effect.send(SetupEffect.ReplaceToMain)
                }
            }
        }
    }

    private fun onUsernameChange(username: String) {
        _state.value = _state.value.copy(username = username, canSubmitData = username.isNotBlank())
    }

    private suspend fun onContinueClick(): SetupEffect? {
        if (_state.value.username.isNotBlank()) {
            _state.value = _state.value.copy(isLoading = true)
            return try {
                userRepository.saveUser(User(_state.value.username))
                SetupEffect.ReplaceToMain
            } finally {
                _state.value = _state.value.copy(isLoading = false)
                null
            }
        } else {
            return null
        }
    }

    override fun onEvent(event: SetupEvent) {
        viewModelScope.launch {
            val effect = when (event) {
                is SetupEvent.OnUsernameChange -> onUsernameChange(event.username)
                is SetupEvent.OnContinueClick -> onContinueClick()
            }

            if (effect is SetupEffect) {
                _effect.send(effect)
            }
        }
    }
}
