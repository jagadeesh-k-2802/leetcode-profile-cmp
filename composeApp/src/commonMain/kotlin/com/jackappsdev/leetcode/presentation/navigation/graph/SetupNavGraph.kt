package com.jackappsdev.leetcode.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jackappsdev.leetcode.presentation.navigation.Routes
import com.jackappsdev.leetcode.presentation.screens.setup_page.SetupRoot

fun NavGraphBuilder.setupNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Routes.Setup.route,
        route = Routes.SetupGraph.route
    ) {
        composable(Routes.Setup.route) {
            SetupRoot(navController)
        }
    }
}
