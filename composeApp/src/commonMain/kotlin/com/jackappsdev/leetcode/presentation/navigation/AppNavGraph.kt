package com.jackappsdev.leetcode.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jackappsdev.leetcode.presentation.AppViewModel
import com.jackappsdev.leetcode.presentation.navigation.graph.mainNavGraph
import com.jackappsdev.leetcode.presentation.navigation.graph.setupNavGraph
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: AppViewModel = koinViewModel()
) {
    val startDestination by viewModel.startDestination.collectAsState()

    startDestination?.let { destination ->
        NavHost(
            navController = navController,
            startDestination = destination
        ) {
            setupNavGraph(navController)
            mainNavGraph(navController)
        }
    }
}
