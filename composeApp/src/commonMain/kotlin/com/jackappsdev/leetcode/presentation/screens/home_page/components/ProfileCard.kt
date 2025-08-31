package com.jackappsdev.leetcode.presentation.screens.home_page.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jackappsdev.leetcode.domain.model.LeetCodeProfile
import com.jackappsdev.leetcode.presentation.components.VerticalDivider
import com.jackappsdev.leetcode.presentation.theme.brightGreen
import com.jackappsdev.leetcode.presentation.theme.ratingYellow
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import com.jackappsdev.leetcode.presentation.theme.spacingMd
import com.jackappsdev.leetcode.presentation.theme.spacingSm
import com.jackappsdev.leetcode.utils.toCommaString
import io.github.alexzhirkevich.cupertino.adaptive.icons.AdaptiveIcons
import io.github.alexzhirkevich.cupertino.adaptive.icons.CheckCircle
import io.github.alexzhirkevich.cupertino.adaptive.icons.Star
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.label_problems
import leetcode.composeapp.generated.resources.label_ranking
import leetcode.composeapp.generated.resources.welcome_back
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ProfileCard(
    modifier: Modifier = Modifier,
    data: LeetCodeProfile,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(spacingLg.dp)) {
            Text(
                text = stringResource(Res.string.welcome_back),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = spacingSm.dp)
            )

            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = data.name,
                        style = MaterialTheme.typography.headlineMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "@${data.username}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                AsyncImage(
                    model = data.avatarUrl,
                    contentDescription = data.username,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            HorizontalDivider(
                Modifier.fillMaxWidth().padding(vertical = spacingMd.dp),
                color = MaterialTheme.colorScheme.outlineVariant,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatEntry(
                    icon = AdaptiveIcons.Outlined.CheckCircle,
                    iconTint = brightGreen,
                    title = stringResource(Res.string.label_problems),
                    value = data.totalSolved
                )

                VerticalDivider()

                StatEntry(
                    icon = AdaptiveIcons.Outlined.Star,
                    iconTint = ratingYellow,
                    title = stringResource(Res.string.label_ranking),
                    value = data.ranking
                )
            }
        }
    }
}

@Composable
private fun StatEntry(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    value: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacingSm.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = iconTint)
        Column {
            Text(text = value.toCommaString(), style = MaterialTheme.typography.titleMedium)
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
