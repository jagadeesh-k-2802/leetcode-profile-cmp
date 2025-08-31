package com.jackappsdev.leetcode.presentation.screens.friend_detail

import com.jackappsdev.leetcode.domain.model.LeetCodeProfile

data class FriendDetailState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val deleteDialogVisible: Boolean = false,
    val data: LeetCodeProfile? = null
)
