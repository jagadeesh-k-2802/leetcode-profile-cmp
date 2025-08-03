package com.jackappsdev.leetcode.presentation.screens.setup_page

import androidx.navigation.NavHostController
import com.jackappsdev.leetcode.presentation.navigation.Routes
import com.jackappsdev.leetcode.presentation.screens.setup_page.event.SetupPageEffect

class SetupEffectHandler(
    private val navController: NavHostController
) {
    fun handleEffect(effect: SetupPageEffect) {
        when (effect) {
            is SetupPageEffect.ReplaceToMain -> replaceToMain()
        }
    }

    private fun replaceToMain() {
        navController.navigate(Routes.Main.route) {
            popUpTo(Routes.Setup.route) {
                inclusive = true
            }
        }
    }
}
