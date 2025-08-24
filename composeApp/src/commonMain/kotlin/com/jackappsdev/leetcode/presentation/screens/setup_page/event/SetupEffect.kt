package com.jackappsdev.leetcode.presentation.screens.setup_page.event

sealed interface SetupEffect {
    data object ReplaceToMain : SetupEffect
}
