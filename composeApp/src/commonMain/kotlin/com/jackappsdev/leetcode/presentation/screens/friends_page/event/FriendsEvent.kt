package com.jackappsdev.leetcode.presentation.screens.friends_page.event

import com.jackappsdev.leetcode.domain.model.Friend

sealed class FriendsEvent {
    object OnRefresh : FriendsEvent()
    data class OnFriendItemClick(val friend: Friend) : FriendsEvent()
    object OnAddClick : FriendsEvent()
    data class OnAddUsernameChange(val username: String) : FriendsEvent()
    object OnSubmitAdd : FriendsEvent()
}
