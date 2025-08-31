package com.jackappsdev.leetcode.presentation.screens.home_page.event

sealed class HomeEvent {
    data object OnRefresh : HomeEvent()
}
