package com.jackappsdev.leetcode.presentation.screens.friends_page

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsRoot(rootNavController: NavController) {
    val viewModel: FriendsViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val effectHandler = remember { FriendsEffectHandler(rootNavController, scope, sheetState) }

    LaunchedEffect(Unit) {
        viewModel.effectFlow.collect { effect ->
            effectHandler.handleEffect(effect)
        }
    }

    FriendsPage(
        state = state,
        sheetState = sheetState,
        onEvent = viewModel::onEvent
    )
}
