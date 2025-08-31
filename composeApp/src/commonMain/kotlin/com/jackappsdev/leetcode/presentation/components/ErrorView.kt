package com.jackappsdev.leetcode.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.presentation.theme.spacingSm
import com.jackappsdev.leetcode.presentation.theme.spacingXss
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.label_try_again
import leetcode.composeapp.generated.resources.text_error
import leetcode.composeapp.generated.resources.title_error
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorView(
    onRetry: () -> Unit,
    title: String = stringResource(Res.string.title_error),
    errorText: String = stringResource(Res.string.text_error),
    retryText: String = stringResource(Res.string.label_try_again),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(spacingXss.dp))
        Text(text = errorText, textAlign = TextAlign.Center)
        Spacer(Modifier.height(spacingSm.dp))
        CustomButton(
            modifier = Modifier.fillMaxWidth(0.35f),
            text = retryText,
            size = ButtonSize.SMALL,
            onClick = onRetry
        )
    }
}
