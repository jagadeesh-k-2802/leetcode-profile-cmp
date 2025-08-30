package com.jackappsdev.leetcode.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.presentation.theme.LeetCodeTheme
import com.jackappsdev.leetcode.presentation.theme.spacingMd
import io.github.alexzhirkevich.cupertino.CupertinoTextField
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveWidget
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.label_enter_username
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
    label: String,
    isError: Boolean = false,
    isEnabled: Boolean = true,
) {
    AdaptiveWidget(
        material = {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                label = { Text(text = label) },
                shape = RoundedCornerShape(spacingMd.dp),
                enabled = isEnabled,
                isError = isError
            )
        },
        cupertino = {
            CupertinoTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                enabled = isEnabled,
                placeholder = { Text(text = label) },
                isError = isError,
            )
        }
    )
}

@Preview
@Composable
fun CustomTextFieldPreview() {
    LeetCodeTheme {
        CustomTextField(
            value = "Hello",
            onValueChange = {},
            label = stringResource(Res.string.label_enter_username)
        )
    }
}
