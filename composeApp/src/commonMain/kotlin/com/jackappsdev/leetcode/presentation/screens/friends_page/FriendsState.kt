package com.jackappsdev.leetcode.presentation.screens.friends_page

import com.jackappsdev.leetcode.constants.Constants.EMPTY_STRING
import com.jackappsdev.leetcode.domain.model.Friend

data class FriendsState(
    val isLoading: Boolean = false,
    val error: Boolean = false,
    val data: List<Friend> = emptyList(),
    val addUsername: String = EMPTY_STRING,
    val canSubmitData: Boolean = false,
    val isSubmitInProgress: Boolean = false,
    val addError: String? = null
)
