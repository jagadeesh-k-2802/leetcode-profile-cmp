package com.jackappsdev.leetcode.presentation.screens.setup_page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SetupRoot(
    navController: NavHostController
) {
    val viewModel: SetupPageViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val effectHandler = remember { SetupEffectHandler(navController) }

    LaunchedEffect(Unit) {
        viewModel.effectFlow.collect { effect ->
            effectHandler.handleEffect(effect)
        }
    }

    SetupPage(
        state = state,
        onEvent = viewModel::onEvent
    )
}
