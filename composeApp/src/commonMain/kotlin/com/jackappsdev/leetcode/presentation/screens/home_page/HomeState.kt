package com.jackappsdev.leetcode.presentation.screens.home_page

import com.jackappsdev.leetcode.domain.model.LeetCodeProfile

data class HomeState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val data: LeetCodeProfile? = null
)
