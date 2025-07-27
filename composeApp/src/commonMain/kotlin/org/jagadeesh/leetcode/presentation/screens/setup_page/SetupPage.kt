package org.jagadeesh.leetcode.presentation.screens.setup_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.label_continue
import leetcode.composeapp.generated.resources.label_enter_username
import leetcode.composeapp.generated.resources.leetcode_logo
import leetcode.composeapp.generated.resources.text_track_and_improve
import leetcode.composeapp.generated.resources.text_welcome
import org.jagadeesh.leetcode.presentation.components.PrimaryButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SetupPage(
    viewModel: SetupPageViewModel = viewModel { SetupPageViewModel() }
) {
    Scaffold { contentPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            Spacer(Modifier.height(96.dp))
            Image(
                painter = painterResource(resource = Res.drawable.leetcode_logo),
                contentDescription = null,
                modifier = Modifier.size(140.dp)
            )
            Spacer(Modifier.height(48.dp))
            Text(
                text = stringResource(Res.string.text_welcome),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(Res.string.text_track_and_improve),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(48.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = stringResource(resource = Res.string.label_enter_username)) },
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(Modifier.height(16.dp))

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(resource = Res.string.label_continue),
                onClick = {}
            )
        }
    }
}
