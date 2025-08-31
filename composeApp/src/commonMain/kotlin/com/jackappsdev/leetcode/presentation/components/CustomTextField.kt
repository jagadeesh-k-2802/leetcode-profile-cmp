package com.jackappsdev.leetcode.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.presentation.theme.LeetCodeTheme
import com.jackappsdev.leetcode.presentation.theme.spacingMd
import io.github.alexzhirkevich.cupertino.CupertinoBorderedTextField
import io.github.alexzhirkevich.cupertino.CupertinoSearchTextFieldDefaults
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
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    maxLines: Int = 1
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
                leadingIcon = leading,
                trailingIcon = trailing,
                isError = isError,
                maxLines = maxLines,
            )
        },
        cupertino = {
            CupertinoBorderedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                enabled = isEnabled,
                colors = CupertinoSearchTextFieldDefaults.colors(),
                placeholder = { Text(text = label) },
                leadingIcon = leading,
                trailingIcon = trailing,
                isError = isError,
                maxLines = maxLines,
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
