package com.jackappsdev.leetcode.presentation.screens.home_page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoot() {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    HomePage(
        state = state,
        onEvent = viewModel::onEvent
    )
}
