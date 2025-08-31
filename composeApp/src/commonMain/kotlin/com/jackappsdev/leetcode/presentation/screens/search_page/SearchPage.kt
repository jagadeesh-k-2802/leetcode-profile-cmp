package com.jackappsdev.leetcode.presentation.screens.search_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.presentation.components.CustomTextField
import com.jackappsdev.leetcode.presentation.components.ErrorView
import com.jackappsdev.leetcode.presentation.components.LoadingView
import com.jackappsdev.leetcode.presentation.card_components.CommonCalendarCard
import com.jackappsdev.leetcode.presentation.card_components.CommonProfileCard
import com.jackappsdev.leetcode.presentation.card_components.CommonGridStatisticsCard
import com.jackappsdev.leetcode.presentation.screens.search_page.event.SearchEvent
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTextButton
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTopAppBar
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.label_done
import leetcode.composeapp.generated.resources.label_enter_username
import leetcode.composeapp.generated.resources.text_search
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun SearchPage(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit
) {
    val scrollState = rememberScrollState()

    AdaptiveScaffold(
        topBar = {
            AdaptiveTopAppBar(
                title = { Text(text = stringResource(Res.string.text_search)) }
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                CustomTextField(
                    value = state.searchQuery,
                    onValueChange = { onEvent(SearchEvent.OnSearchQueryChange(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(resource = Res.string.label_enter_username),
                    isEnabled = !state.isLoading,
                    trailing = {
                        AdaptiveTextButton(
                            onClick = { onEvent(SearchEvent.OnSearch) }
                        ) {
                            Text(text = stringResource(Res.string.label_done))
                        }
                    }
                )
                Spacer(Modifier.height(spacingLg.dp))

                when {
                    state.isLoading -> {
                        Spacer(modifier = Modifier.height(128.dp))
                        LoadingView(modifier = Modifier.fillMaxSize())
                    }

                    state.isError -> {
                        Spacer(modifier = Modifier.height(128.dp))
                        ErrorView(
                            modifier = Modifier.fillMaxSize(),
                            onRetry = { onEvent(SearchEvent.OnRefresh) }
                        )
                    }

                    state.data != null -> {
                        CommonProfileCard(data = state.data)
                        Spacer(modifier = Modifier.height(spacingLg.dp))
                        CommonGridStatisticsCard(data = state.data)
                        Spacer(modifier = Modifier.height(spacingLg.dp))
                        CommonCalendarCard(data = state.data)
                        Spacer(modifier = Modifier.height(128.dp))
                    }
                }
            }
        }
    }
}
