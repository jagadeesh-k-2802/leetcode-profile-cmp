package com.jackappsdev.leetcode.presentation.screens.friends_page.event

sealed class FriendsEffect {
    data class NavigateToFriendDetail(val username: String) : FriendsEffect()
    data object ToggleAddSheetVisibility : FriendsEffect()
}
