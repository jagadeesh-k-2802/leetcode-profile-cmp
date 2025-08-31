package com.jackappsdev.leetcode.presentation.screens.search_page

import com.jackappsdev.leetcode.constants.Constants.EMPTY_STRING
import com.jackappsdev.leetcode.domain.model.LeetCodeProfile

data class SearchState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val searchQuery: String = EMPTY_STRING,
    val canSubmitData: Boolean = false,
    val data: LeetCodeProfile? = null
)
