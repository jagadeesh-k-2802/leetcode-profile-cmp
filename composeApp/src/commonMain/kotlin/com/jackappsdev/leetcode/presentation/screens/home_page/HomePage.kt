package com.jackappsdev.leetcode.presentation.screens.home_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.presentation.components.ErrorView
import com.jackappsdev.leetcode.presentation.components.LoadingView
import com.jackappsdev.leetcode.presentation.screens.home_page.components.ActivityGraphCard
import com.jackappsdev.leetcode.presentation.screens.home_page.components.ProfileCard
import com.jackappsdev.leetcode.presentation.screens.home_page.components.StatisticsCard
import com.jackappsdev.leetcode.presentation.screens.home_page.event.HomeEvent
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTopAppBar
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.text_home
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun HomePage(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    val scrollState = rememberScrollState()

    AdaptiveScaffold(
        topBar = {
            AdaptiveTopAppBar(
                title = { Text(text = stringResource(Res.string.text_home)) }
            )
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                .padding(spacingLg.dp)
        ) {
            when {
                state.isLoading -> {
                    LoadingView(modifier = Modifier.fillMaxSize())
                }

                state.isError -> {
                    ErrorView(
                        modifier = Modifier.fillMaxSize(),
                        onRetry = { onEvent(HomeEvent.OnRefresh) }
                    )
                }

                state.data != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                    ) {
                        ProfileCard(data = state.data)
                        Spacer(modifier = Modifier.height(spacingLg.dp))
                        StatisticsCard(data = state.data)
                        Spacer(modifier = Modifier.height(spacingLg.dp))
                        ActivityGraphCard(data = state.data)
                        Spacer(modifier = Modifier.height(128.dp))
                    }
                }
            }
        }
    }
}
