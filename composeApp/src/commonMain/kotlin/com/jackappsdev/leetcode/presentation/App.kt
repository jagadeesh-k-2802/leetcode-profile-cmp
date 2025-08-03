package com.jackappsdev.leetcode.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.jackappsdev.leetcode.presentation.navigation.AppNavGraph
import com.jackappsdev.leetcode.presentation.theme.LeetCodeTheme

@Composable
fun App() {
    val navController = rememberNavController()

    LeetCodeTheme {
        AppNavGraph(navController)
    }
}
