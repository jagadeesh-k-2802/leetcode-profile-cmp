package com.jackappsdev.leetcode.presentation.screens.friends_page

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.navigation.NavController
import com.jackappsdev.leetcode.presentation.navigation.Routes
import com.jackappsdev.leetcode.presentation.screens.friends_page.event.FriendsEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class FriendsEffectHandler(
    private val navController: NavController,
    private val scope: CoroutineScope,
    private val sheetState: SheetState
) {

    fun handleEffect(effect: FriendsEffect) {
        when (effect) {
            is FriendsEffect.NavigateToFriendDetail -> navigateToFriendDetail(effect.username)
            is FriendsEffect.ToggleAddSheetVisibility -> toggleAddSheetVisibility()
        }
    }

    private fun navigateToFriendDetail(username: String) {
        navController.navigate(Routes.FriendDetail(username))
    }

    private fun toggleAddSheetVisibility() {
        scope.launch {
            if (sheetState.isVisible) {
                sheetState.hide()
            } else {
                sheetState.show()
            }
        }
    }
}
