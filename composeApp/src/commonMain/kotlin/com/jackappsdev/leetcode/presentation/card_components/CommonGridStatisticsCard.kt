package com.jackappsdev.leetcode.presentation.card_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.domain.model.LeetCodeProfile
import com.jackappsdev.leetcode.presentation.theme.greenLevel4
import com.jackappsdev.leetcode.presentation.theme.red
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import com.jackappsdev.leetcode.presentation.theme.spacingMd
import com.jackappsdev.leetcode.presentation.theme.teal
import com.jackappsdev.leetcode.presentation.theme.yellow
import com.jackappsdev.leetcode.utils.toCommaString
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.global_rank
import leetcode.composeapp.generated.resources.label_easy
import leetcode.composeapp.generated.resources.label_hard
import leetcode.composeapp.generated.resources.label_medium_full
import leetcode.composeapp.generated.resources.label_total_solved
import leetcode.composeapp.generated.resources.title_problem_solving_stats
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CommonGridStatisticsCard(
    modifier: Modifier = Modifier,
    data: LeetCodeProfile
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(spacingLg.dp)) {
            Text(
                text = stringResource(Res.string.title_problem_solving_stats),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(spacingLg.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatBox(
                    value = data.totalSolved.toCommaString(),
                    label = stringResource(Res.string.label_total_solved),
                    color = greenLevel4
                )
                Spacer(modifier = Modifier.width(spacingMd.dp))
                StatBox(
                    value = "${data.easySolved.toCommaString()}/${data.totalEasy.toCommaString()}",
                    label = stringResource(Res.string.label_easy),
                    color = teal
                )
            }
            Spacer(modifier = Modifier.height(spacingMd.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatBox(
                    value = "${data.mediumSolved.toCommaString()}/${data.totalMedium.toCommaString()}",
                    label = stringResource(Res.string.label_medium_full),
                    color = yellow
                )
                Spacer(modifier = Modifier.width(spacingMd.dp))
                StatBox(
                    value = "${data.hardSolved.toCommaString()}/${data.totalHard.toCommaString()}",
                    label = stringResource(Res.string.label_hard),
                    color = red
                )
            }
            Spacer(modifier = Modifier.height(spacingLg.dp))
            Text(
                text = stringResource(Res.string.global_rank, data.ranking.toCommaString()),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
private fun RowScope.StatBox(
    value: String,
    label: String,
    color: Color
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(spacingLg.dp))
            .background(color = color.copy(alpha = 0.08f))
            .padding(spacingMd.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value, color = color, style = MaterialTheme.typography.bodyLarge)
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.scrim
        )
    }
}
