package com.jackappsdev.leetcode.presentation.screens.setup_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackappsdev.leetcode.domain.model.User
import com.jackappsdev.leetcode.domain.repository.UserRepository
import com.jackappsdev.leetcode.presentation.base.EventDrivenViewModel
import com.jackappsdev.leetcode.presentation.screens.setup_page.event.SetupPageEffect
import com.jackappsdev.leetcode.presentation.screens.setup_page.event.SetupPageEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
class SetupPageViewModel(
    private val userRepository: UserRepository
) : ViewModel(), EventDrivenViewModel<SetupPageState, SetupPageEvent, SetupPageEffect> {
    private val _state = MutableStateFlow(SetupPageState())
    override val state: StateFlow<SetupPageState> = _state.asStateFlow()

    private val _effect = Channel<SetupPageEffect>()
    override val effectFlow = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            userRepository.getUser().collect { user ->
                if (user != null) {
                    _effect.send(SetupPageEffect.ReplaceToMain)
                }
            }
        }
    }

    private fun onUsernameChange(username: String) {
        _state.value = _state.value.copy(username = username)
    }

    private suspend fun onContinueClick(): SetupPageEffect? {
        if (_state.value.username.isNotBlank()) {
            _state.value = _state.value.copy(isLoading = true)
            return try {
                userRepository.saveUser(User(_state.value.username))
                SetupPageEffect.ReplaceToMain
            } finally {
                _state.value = _state.value.copy(isLoading = false)
                null
            }
        } else {
            return null
        }
    }

    override fun onEvent(event: SetupPageEvent) {
        viewModelScope.launch {
            val effect = when (event) {
                is SetupPageEvent.OnUsernameChange -> onUsernameChange(event.username)
                is SetupPageEvent.OnContinueClick -> onContinueClick()
            }

            if (effect is SetupPageEffect) {
                _effect.send(effect)
            }
        }
    }
}
