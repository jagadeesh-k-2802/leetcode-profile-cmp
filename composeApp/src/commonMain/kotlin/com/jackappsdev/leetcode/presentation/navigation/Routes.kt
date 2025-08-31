package com.jackappsdev.leetcode.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String) {
    // Graph Routes
    @Serializable
    data object SetupGraph : Routes("setup_graph")
    @Serializable
    data object MainGraph : Routes("main_graph")

    // Screen Routes
    @Serializable
    data object Setup : Routes("setup")
    @Serializable
    data object Main : Routes("main")
    @Serializable
    data class FriendDetail(val username: String) : Routes("friend-detail/{username}")

    // Main Tab Routes
    @Serializable
    data object Home : Routes("home")
    @Serializable
    data object Search : Routes("search")
    @Serializable
    data object Friends : Routes("friends")
}
