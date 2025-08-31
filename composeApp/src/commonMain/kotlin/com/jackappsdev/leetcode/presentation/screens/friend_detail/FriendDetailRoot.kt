package com.jackappsdev.leetcode.presentation.screens.friend_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import com.jackappsdev.leetcode.constants.Constants.EMPTY_STRING
import com.jackappsdev.leetcode.presentation.navigation.Routes
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun FriendDetailRoot(rootNavController: NavHostController) {
    val route = remember { rootNavController.currentBackStackEntry?.toRoute<Routes.FriendDetail>() }
    val username = route?.username ?: EMPTY_STRING
    val viewModel: FriendDetailViewModel = koinViewModel(parameters = { parametersOf(username) })
    val state by viewModel.state.collectAsState()
    val effectHandler = remember { FriendDetailEffectHandler(rootNavController) }

    LaunchedEffect(Unit) {
        viewModel.effectFlow.collect { effect ->
            effectHandler.handleEffect(effect)
        }
    }

    FriendDetailPage(
        state = state,
        onEvent = viewModel::onEvent,
    )
}
