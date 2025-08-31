package com.jackappsdev.leetcode.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jackappsdev.leetcode.presentation.navigation.Routes
import com.jackappsdev.leetcode.presentation.screens.friend_detail.FriendDetailRoot
import com.jackappsdev.leetcode.presentation.screens.main_page.MainRoot

fun NavGraphBuilder.mainNavGraph(rootNavController: NavHostController) {
    navigation<Routes.MainGraph>(startDestination = Routes.Main) {
        composable<Routes.Main> {
            MainRoot(rootNavController)
        }

        composable<Routes.FriendDetail> {
            FriendDetailRoot(rootNavController)
        }
    }
}
