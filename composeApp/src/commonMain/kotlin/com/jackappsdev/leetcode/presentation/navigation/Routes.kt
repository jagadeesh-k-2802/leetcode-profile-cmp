package com.jackappsdev.leetcode.presentation.navigation

sealed class Routes(val route: String) {
    // Graph Routes
    data object SetupGraph : Routes("setup_graph")
    data object MainGraph : Routes("main_graph")

    // Screen Routes
    data object Setup : Routes("setup")
    data object Main : Routes("main")
    
    // Main Tab Routes
    data object Home : Routes("home")
    data object Search : Routes("search")
    data object Friends : Routes("friends")
}
