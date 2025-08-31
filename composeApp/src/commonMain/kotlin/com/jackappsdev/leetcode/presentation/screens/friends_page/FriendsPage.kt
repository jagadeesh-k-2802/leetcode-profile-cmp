package com.jackappsdev.leetcode.presentation.screens.friends_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.presentation.components.ErrorView
import com.jackappsdev.leetcode.presentation.components.LoadingView
import com.jackappsdev.leetcode.presentation.screens.friends_page.components.AddFriendBottomSheet
import com.jackappsdev.leetcode.presentation.screens.friends_page.components.FriendRow
import com.jackappsdev.leetcode.presentation.screens.friends_page.event.FriendsEvent
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveIconButton
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTopAppBar
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import io.github.alexzhirkevich.cupertino.adaptive.icons.AdaptiveIcons
import io.github.alexzhirkevich.cupertino.adaptive.icons.AddCircle
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.text_friends
import leetcode.composeapp.generated.resources.text_no_friends
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalAdaptiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FriendsPage(
    state: FriendsState,
    sheetState: SheetState,
    onEvent: (FriendsEvent) -> Unit,
) {
    if (sheetState.isVisible) {
        AddFriendBottomSheet(
            state = state,
            sheetState = sheetState,
            onEvent = onEvent
        )
    }

    AdaptiveScaffold(
        topBar = {
            AdaptiveTopAppBar(
                title = { Text(text = stringResource(Res.string.text_friends)) },
                actions = {
                    AdaptiveIconButton(onClick = { onEvent(FriendsEvent.OnAddClick) }) {
                        Icon(
                            imageVector = AdaptiveIcons.Outlined.AddCircle,
                            contentDescription = null
                        )
                    }
                }
            )
        }
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

                state.error -> {
                    ErrorView(
                        modifier = Modifier.fillMaxSize(),
                        onRetry = { onEvent(FriendsEvent.OnRefresh) }
                    )
                }

                state.data.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = stringResource(Res.string.text_no_friends))
                    }
                }

                else -> {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(state.data) { friend ->
                            FriendRow(
                                friend = friend,
                                onClick = { onEvent(FriendsEvent.OnFriendItemClick(friend)) }
                            )
                        }
                    }
                }
            }
        }
    }
}
