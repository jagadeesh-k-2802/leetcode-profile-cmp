package com.jackappsdev.leetcode.presentation.screens.main_page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jackappsdev.leetcode.presentation.navigation.Routes
import com.jackappsdev.leetcode.presentation.navigation.navigateWithState
import com.jackappsdev.leetcode.presentation.screens.friends_page.FriendsRoot
import com.jackappsdev.leetcode.presentation.screens.home_page.HomeRoot
import com.jackappsdev.leetcode.presentation.screens.main_page.components.MainBottomBar
import com.jackappsdev.leetcode.presentation.screens.search_page.SearchRoot
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun MainPage() {
    val navController = rememberNavController()
    var currentRoute by mutableStateOf(Routes.Home.route)

    AdaptiveScaffold(
        bottomBar = {
            MainBottomBar(currentRoute) { route ->
                currentRoute = route
                navController.navigateWithState(route)
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.Home.route
        ) {
            composable(Routes.Home.route) {
                HomeRoot()
            }

            composable(Routes.Search.route) {
                SearchRoot()
            }

            composable(Routes.Friends.route) {
                FriendsRoot()
            }
        }
    }
}
