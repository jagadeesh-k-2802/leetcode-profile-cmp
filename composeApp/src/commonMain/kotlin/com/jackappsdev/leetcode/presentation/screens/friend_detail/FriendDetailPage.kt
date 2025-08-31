package com.jackappsdev.leetcode.presentation.screens.friend_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.presentation.card_components.CommonBadgesCard
import com.jackappsdev.leetcode.presentation.card_components.CommonCalendarCard
import com.jackappsdev.leetcode.presentation.card_components.CommonGridStatisticsCard
import com.jackappsdev.leetcode.presentation.card_components.CommonProfileCard
import com.jackappsdev.leetcode.presentation.components.ErrorView
import com.jackappsdev.leetcode.presentation.components.LoadingView
import com.jackappsdev.leetcode.presentation.screens.friend_detail.event.FriendDetailEvent
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveAlertDialogNative
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveIconButton
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTopAppBar
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import io.github.alexzhirkevich.cupertino.adaptive.icons.AdaptiveIcons
import io.github.alexzhirkevich.cupertino.adaptive.icons.Delete
import io.github.alexzhirkevich.cupertino.adaptive.icons.KeyboardArrowLeft
import io.github.alexzhirkevich.cupertino.cancel
import io.github.alexzhirkevich.cupertino.destructive
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.text_delete_message
import leetcode.composeapp.generated.resources.text_delete_title
import leetcode.composeapp.generated.resources.text_friend
import leetcode.composeapp.generated.resources.text_no
import leetcode.composeapp.generated.resources.text_yes
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun FriendDetailPage(
    state: FriendDetailState,
    onEvent: (FriendDetailEvent) -> Unit
) {
    val scrollState = rememberScrollState()

    if (state.deleteDialogVisible) {
        val textYes = stringResource(Res.string.text_yes)
        val textNo = stringResource(Res.string.text_no)

        AdaptiveAlertDialogNative(
            onDismissRequest = { onEvent(FriendDetailEvent.OnToggleDeleteDialogVisible) },
            title = stringResource(Res.string.text_delete_title),
            message = stringResource(Res.string.text_delete_message),
            buttons = {
                destructive(
                    title = textYes,
                    onClick = { onEvent(FriendDetailEvent.OnDeleteFriend) }
                )
                cancel(
                    title = textNo,
                    onClick = { onEvent(FriendDetailEvent.OnToggleDeleteDialogVisible) }
                )
            }
        )
    }

    AdaptiveScaffold(
        topBar = {
            AdaptiveTopAppBar(
                navigationIcon = {
                    AdaptiveIconButton(onClick = { onEvent(FriendDetailEvent.OnBack) }) {
                        Icon(
                            imageVector = AdaptiveIcons.Outlined.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                },
                title = { Text(text = stringResource(Res.string.text_friend)) },
                actions = {
                    AdaptiveIconButton(onClick = { onEvent(FriendDetailEvent.OnToggleDeleteDialogVisible) }) {
                        Icon(
                            imageVector = AdaptiveIcons.Outlined.Delete,
                            contentDescription = null
                        )
                    }
                }
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
                when {
                    state.isLoading -> {
                        LoadingView(modifier = Modifier.fillMaxSize())
                    }

                    state.isError -> {
                        ErrorView(
                            onRetry = { onEvent(FriendDetailEvent.OnRefresh) },
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    state.data != null -> {
                        CommonProfileCard(data = state.data)
                        Spacer(modifier = Modifier.height(spacingLg.dp))
                        CommonGridStatisticsCard(data = state.data)
                        Spacer(modifier = Modifier.height(spacingLg.dp))
                        if (state.data.badges.isNotEmpty()) {
                            CommonBadgesCard(data = state.data)
                            Spacer(modifier = Modifier.height(spacingLg.dp))
                        }
                        CommonCalendarCard(data = state.data)
                        Spacer(modifier = Modifier.height(128.dp))
                    }
                }
            }
        }
    }
}
