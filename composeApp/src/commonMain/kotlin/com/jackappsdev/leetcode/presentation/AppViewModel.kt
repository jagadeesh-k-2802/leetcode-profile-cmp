package com.jackappsdev.leetcode.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackappsdev.leetcode.domain.repository.UserRepository
import com.jackappsdev.leetcode.presentation.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
class AppViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _startDestination = MutableStateFlow<Routes?>(null)
    val startDestination: StateFlow<Routes?> = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            userRepository.getUser().collect { user ->
                _startDestination.value = if (user != null) {
                    Routes.MainGraph
                } else {
                    Routes.SetupGraph
                }
            }
        }
    }
}
