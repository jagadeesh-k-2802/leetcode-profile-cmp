package com.jackappsdev.leetcode.presentation.screens.main_page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
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
fun MainPage(rootNavController: NavController) {
    val navController = rememberNavController()
    var currentRoute by rememberSaveable(stateSaver = RouteSaver) { mutableStateOf(Routes.Home) }

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
            startDestination = Routes.Home
        ) {
            composable<Routes.Home> { HomeRoot() }
            composable<Routes.Search> { SearchRoot() }
            composable<Routes.Friends> { FriendsRoot(rootNavController) }
        }
    }
}

private val RouteSaver = Saver<Routes, String>(
    save = { route -> route.route },
    restore = { route ->
        when (route) {
            Routes.Search.route -> Routes.Search
            Routes.Friends.route -> Routes.Friends
            else -> Routes.Home
        }
    }
)
