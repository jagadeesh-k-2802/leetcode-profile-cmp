package com.jackappsdev.leetcode.presentation.screens.search_page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTopAppBar
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.text_search
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun SearchPage() {
    AdaptiveScaffold(
        topBar = {
            AdaptiveTopAppBar(
                title = { Text(text = stringResource(Res.string.text_search)) }
            )
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(Res.string.text_search))
        }
    }
}
