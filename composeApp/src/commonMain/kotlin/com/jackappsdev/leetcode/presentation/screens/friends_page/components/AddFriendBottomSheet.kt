package com.jackappsdev.leetcode.presentation.screens.friends_page.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.presentation.components.CustomBottomSheet
import com.jackappsdev.leetcode.presentation.components.CustomButton
import com.jackappsdev.leetcode.presentation.components.CustomTextField
import com.jackappsdev.leetcode.presentation.screens.friends_page.FriendsState
import com.jackappsdev.leetcode.presentation.screens.friends_page.event.FriendsEvent
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.label_add_friend
import leetcode.composeapp.generated.resources.label_enter_username
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriendBottomSheet(
    modifier: Modifier = Modifier,
    state: FriendsState,
    sheetState: SheetState,
    onEvent: (FriendsEvent) -> Unit
) {
    CustomBottomSheet(sheetState = sheetState) {
        Column(
            modifier = modifier
                .padding(spacingLg.dp)
                .fillMaxSize()
        ) {
            CustomTextField(
                value = state.addUsername,
                onValueChange = { onEvent(FriendsEvent.OnAddUsernameChange(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(resource = Res.string.label_enter_username),
                isEnabled = !state.isLoading && !state.isSubmitInProgress,
                isError = state.addError != null
            )
            Spacer(Modifier.height(spacingLg.dp))
            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(resource = Res.string.label_add_friend),
                onClick = { onEvent(FriendsEvent.OnSubmitAdd) },
                enabled = state.canSubmitData && !state.isSubmitInProgress
            )
        }
    }
}
