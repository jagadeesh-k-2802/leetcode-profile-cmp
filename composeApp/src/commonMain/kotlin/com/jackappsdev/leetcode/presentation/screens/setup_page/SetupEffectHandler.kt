package com.jackappsdev.leetcode.presentation.screens.setup_page

import androidx.navigation.NavHostController
import com.jackappsdev.leetcode.presentation.navigation.Routes
import com.jackappsdev.leetcode.presentation.screens.setup_page.event.SetupEffect

class SetupEffectHandler(
    private val navController: NavHostController
) {
    fun handleEffect(effect: SetupEffect) {
        when (effect) {
            is SetupEffect.ReplaceToMain -> replaceToMain()
        }
    }

    private fun replaceToMain() {
        navController.navigate(Routes.Main) {
            popUpTo(Routes.Setup) {
                inclusive = true
            }
        }
    }
}
