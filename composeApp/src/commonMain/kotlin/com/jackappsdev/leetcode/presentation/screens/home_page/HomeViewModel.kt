package com.jackappsdev.leetcode.presentation.screens.home_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackappsdev.leetcode.domain.repository.LeetCodeRepository
import com.jackappsdev.leetcode.domain.repository.UserRepository
import com.jackappsdev.leetcode.presentation.base.EventDrivenViewModel
import com.jackappsdev.leetcode.presentation.screens.home_page.event.HomeEffect
import com.jackappsdev.leetcode.presentation.screens.home_page.event.HomeEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
class HomeViewModel(
    private val leetCodeRepository: LeetCodeRepository,
    private val userRepository: UserRepository
) : ViewModel(), EventDrivenViewModel<HomeState, HomeEvent, HomeEffect> {

    private val _state = MutableStateFlow(HomeState())
    override val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _effect = Channel<HomeEffect>()
    override val effectFlow = _effect.receiveAsFlow()

    init {
        onInit()
    }

    private fun onInit() {
        viewModelScope.launch {
            val username = userRepository.getUser().first()?.username ?: return@launch

            leetCodeRepository.getProfile(username).collectLatest { result ->
                result.fold(
                    onSuccess = { profile ->
                        _state.value = _state.value.copy(
                            data = profile,
                            isLoading = false,
                            isError = false
                        )
                    },
                    onFailure = { error ->
                        error.printStackTrace()
                        _state.value = _state.value.copy(
                            data = null,
                            isLoading = false,
                            isError = true
                        )
                    }
                )
            }
        }
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.RefreshProfile -> onInit()
        }
    }
}
