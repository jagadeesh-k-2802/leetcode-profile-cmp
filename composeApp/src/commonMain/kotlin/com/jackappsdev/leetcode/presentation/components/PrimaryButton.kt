package com.jackappsdev.leetcode.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.presentation.theme.LeetCodeTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    LeetCodeTheme {
        PrimaryButton(
            text = "Continue",
            onClick = { /* Do something */ },
            enabled = true,
            modifier = Modifier.padding(16.dp)
        )
    }
}
