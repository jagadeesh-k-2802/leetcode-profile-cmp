package com.jackappsdev.leetcode.presentation.screens.search_page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchRoot() {
    val viewModel: SearchViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    SearchPage(
        state = state,
        onEvent = viewModel::onEvent
    )
}
