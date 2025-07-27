package org.jagadeesh.leetcode.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jagadeesh.leetcode.presentation.navigation.Routes
import org.jagadeesh.leetcode.presentation.screens.setup_page.SetupPage
import org.jagadeesh.leetcode.presentation.theme.LeetCodeTheme

@Composable
fun App() {
    val navController = rememberNavController()

    LeetCodeTheme {
        NavHost(
            navController = navController,
            startDestination = Routes.Setup.name
        ) {
            composable(Routes.Setup.name) {
                SetupPage()
            }
        }
    }
}
