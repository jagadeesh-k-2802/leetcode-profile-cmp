package com.jackappsdev.leetcode.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jackappsdev.leetcode.presentation.navigation.Routes
import com.jackappsdev.leetcode.presentation.screens.main_page.MainRoot

fun NavGraphBuilder.mainNavGraph() {
    navigation(
        startDestination = Routes.Main.route,
        route = Routes.MainGraph.route
    ) {
        composable(Routes.Main.route) {
            MainRoot()
        }
    }
}
