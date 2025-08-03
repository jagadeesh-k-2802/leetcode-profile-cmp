package com.jackappsdev.leetcode.presentation.screens.setup_page.event

sealed interface SetupPageEvent {
    data class OnUsernameChange(val username: String) : SetupPageEvent
    data object OnContinueClick : SetupPageEvent
}
