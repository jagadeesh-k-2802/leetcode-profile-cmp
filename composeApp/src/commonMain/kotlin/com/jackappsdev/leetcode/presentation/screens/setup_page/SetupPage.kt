package com.jackappsdev.leetcode.presentation.screens.setup_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.presentation.components.CustomButton
import com.jackappsdev.leetcode.presentation.components.CustomTextField
import com.jackappsdev.leetcode.presentation.screens.setup_page.event.SetupEvent
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import com.jackappsdev.leetcode.presentation.theme.spacingXs
import com.jackappsdev.leetcode.presentation.theme.spacingXxl
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveCircularProgressIndicator
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.label_continue
import leetcode.composeapp.generated.resources.label_enter_username
import leetcode.composeapp.generated.resources.leetcode_logo
import leetcode.composeapp.generated.resources.text_track_and_improve
import leetcode.composeapp.generated.resources.text_welcome
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun SetupPage(
    state: SetupState,
    onEvent: (SetupEvent) -> Unit
) {
    val scrollState = rememberScrollState()

    AdaptiveScaffold { contentPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(contentPadding)
                .padding(spacingLg.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(Modifier.height(128.dp))
            Image(
                painter = painterResource(resource = Res.drawable.leetcode_logo),
                contentDescription = null,
                modifier = Modifier.size(96.dp)
            )
            Spacer(Modifier.height(spacingXxl.dp))
            Text(
                text = stringResource(Res.string.text_welcome),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(spacingXs.dp))
            Text(
                text = stringResource(Res.string.text_track_and_improve),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(spacingXxl.dp))
            CustomTextField(
                value = state.username,
                onValueChange = { onEvent(SetupEvent.OnUsernameChange(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(resource = Res.string.label_enter_username),
                isEnabled = !state.isLoading
            )
            Spacer(Modifier.height(spacingLg.dp))

            if (state.isLoading) {
                AdaptiveCircularProgressIndicator()
            } else {
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(resource = Res.string.label_continue),
                    onClick = { onEvent(SetupEvent.OnContinueClick) },
                    enabled = state.canSubmitData
                )
            }
        }
    }
}
