package com.jackappsdev.leetcode.presentation.card_components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jackappsdev.leetcode.domain.model.LeetCodeProfile
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import com.jackappsdev.leetcode.presentation.theme.spacingMd
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.title_badges
import org.jetbrains.compose.resources.stringResource

@Composable
fun CommonBadgesCard(
    modifier: Modifier = Modifier,
    data: LeetCodeProfile
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(modifier = Modifier.padding(spacingLg.dp)) {
            Text(
                text = stringResource(Res.string.title_badges),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(spacingMd.dp))
            BadgesView(badges = data.badges)
        }
    }
}

@Composable
private fun BadgesView(
    modifier: Modifier = Modifier,
    badges: List<String>,
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = modifier.horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(spacingMd.dp)
    ) {
        badges.map { badge ->
            println(badge)
            AsyncImage(
                model = badge,
                contentDescription = null,
                modifier = Modifier.size(48.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}
