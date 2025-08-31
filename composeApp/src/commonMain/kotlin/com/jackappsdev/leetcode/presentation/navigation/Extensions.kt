package com.jackappsdev.leetcode.presentation.navigation

import androidx.navigation.NavController

fun NavController.navigateWithState(route: Routes) {
    navigate(route) {
        popUpTo(graph.startDestinationId) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
