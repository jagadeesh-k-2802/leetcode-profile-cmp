package com.jackappsdev.leetcode.presentation.screens.setup_page

import com.jackappsdev.leetcode.constants.Constants.EMPTY_STRING

data class SetupState(
    val username: String = EMPTY_STRING,
    val canSubmitData: Boolean = false,
    val isLoading: Boolean = false
)
