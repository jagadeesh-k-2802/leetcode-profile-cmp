package com.jackappsdev.leetcode.presentation.navigation

import androidx.navigation.NavController

fun NavController.navigateWithState(route: String) {
    navigate(route) {
        popUpTo(graph.startDestinationId) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
