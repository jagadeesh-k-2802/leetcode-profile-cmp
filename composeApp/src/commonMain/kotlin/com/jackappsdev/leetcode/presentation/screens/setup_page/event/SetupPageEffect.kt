package com.jackappsdev.leetcode.presentation.screens.setup_page.event

sealed interface SetupPageEffect {
    data object ReplaceToMain : SetupPageEffect
}
