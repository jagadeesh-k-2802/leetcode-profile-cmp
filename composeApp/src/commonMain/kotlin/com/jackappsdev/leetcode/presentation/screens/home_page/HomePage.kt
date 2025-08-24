package com.jackappsdev.leetcode.presentation.screens.home_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jackappsdev.leetcode.presentation.screens.home_page.components.ProfileCard
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveCircularProgressIndicator
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTopAppBar
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.text_error
import leetcode.composeapp.generated.resources.text_home
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun HomePage(
    state: HomeState
) {
    AdaptiveScaffold(
        topBar = {
            AdaptiveTopAppBar(
                title = { Text(text = stringResource(Res.string.text_home)) }
            )
        },
    ) { contentPadding ->
        when {
            state.isLoading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AdaptiveCircularProgressIndicator()
                }
            }

            state.isError -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(Res.string.text_error))
                }
            }

            state.data != null -> {
                ProfileCard(profile = state.data)
            }
        }
    }
}
