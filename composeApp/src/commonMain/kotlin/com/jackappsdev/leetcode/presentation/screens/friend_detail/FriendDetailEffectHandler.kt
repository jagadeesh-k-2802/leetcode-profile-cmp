package com.jackappsdev.leetcode.presentation.screens.friend_detail

import androidx.navigation.NavController
import com.jackappsdev.leetcode.presentation.screens.friend_detail.event.FriendDetailEffect

class FriendDetailEffectHandler(
    private val navController: NavController
) {
    fun handleEffect(effect: FriendDetailEffect) {
        when (effect) {
            is FriendDetailEffect.GoBack -> goBack()
        }
    }

    private fun goBack() {
        navController.popBackStack()
    }
}
