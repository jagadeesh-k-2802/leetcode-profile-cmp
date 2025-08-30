package com.jackappsdev.leetcode.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.presentation.theme.LeetCodeTheme
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import com.jackappsdev.leetcode.presentation.theme.spacingXs
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveButton
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    AdaptiveButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(spacingXs.dp)
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    LeetCodeTheme {
        CustomButton(
            text = "Continue",
            onClick = { /* Do something */ },
            enabled = true,
            modifier = Modifier.padding(spacingLg.dp)
        )
    }
}
