package com.jackappsdev.leetcode.presentation.screens.friends_page.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jackappsdev.leetcode.domain.model.Friend
import com.jackappsdev.leetcode.presentation.theme.brightGreen
import com.jackappsdev.leetcode.presentation.theme.spacingMd
import com.jackappsdev.leetcode.presentation.theme.spacingSm
import com.jackappsdev.leetcode.presentation.theme.spacingXss
import com.jackappsdev.leetcode.presentation.theme.yellow
import com.jackappsdev.leetcode.utils.formatWithK
import com.jackappsdev.leetcode.utils.toCommaString
import io.github.alexzhirkevich.cupertino.adaptive.icons.AdaptiveIcons
import io.github.alexzhirkevich.cupertino.adaptive.icons.CheckCircle
import io.github.alexzhirkevich.cupertino.adaptive.icons.Star

@Composable
fun FriendRow(
    modifier: Modifier = Modifier,
    friend: Friend,
    onClick: (Friend) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        onClick = { onClick(friend) }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(spacingMd.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacingMd.dp)
        ) {
            AsyncImage(
                model = friend.avatarUrl,
                contentDescription = friend.username,
                modifier = Modifier.height(48.dp).clip(CircleShape)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = friend.username, style = MaterialTheme.typography.titleMedium)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacingSm.dp)
                ) {
                    StatBox(
                        value = friend.problemsSolved.toCommaString(),
                        icon = AdaptiveIcons.Outlined.CheckCircle,
                        color = brightGreen
                    )
                    StatBox(
                        value = friend.ranking.formatWithK(),
                        icon = AdaptiveIcons.Outlined.Star,
                        color = yellow
                    )
                }
            }
        }
    }
}

@Composable
private fun StatBox(
    value: String,
    icon: ImageVector,
    color: Color,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(0.25f))
            .padding(horizontal = spacingXss.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(12.dp)
        )
        Spacer(Modifier.width(spacingXss.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.scrim
        )
    }
}
