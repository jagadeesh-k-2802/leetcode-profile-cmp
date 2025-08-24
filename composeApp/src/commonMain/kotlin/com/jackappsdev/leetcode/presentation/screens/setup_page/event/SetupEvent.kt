package com.jackappsdev.leetcode.presentation.screens.setup_page.event

sealed interface SetupEvent {
    data class OnUsernameChange(val username: String) : SetupEvent
    data object OnContinueClick : SetupEvent
}
