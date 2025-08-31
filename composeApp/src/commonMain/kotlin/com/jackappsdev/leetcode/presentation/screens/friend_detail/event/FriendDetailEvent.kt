package com.jackappsdev.leetcode.presentation.screens.friend_detail.event

sealed class FriendDetailEvent {
    object OnRefresh : FriendDetailEvent()
    object OnBack : FriendDetailEvent()
    object OnToggleDeleteDialogVisible : FriendDetailEvent()
    object OnDeleteFriend : FriendDetailEvent()
}
