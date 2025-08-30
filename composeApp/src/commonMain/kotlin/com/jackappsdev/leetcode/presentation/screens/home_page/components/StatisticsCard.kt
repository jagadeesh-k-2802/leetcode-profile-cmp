package com.jackappsdev.leetcode.presentation.screens.home_page.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.domain.model.LeetCodeProfile
import com.jackappsdev.leetcode.presentation.components.VerticalDivider
import com.jackappsdev.leetcode.presentation.theme.red
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import com.jackappsdev.leetcode.presentation.theme.spacingMd
import com.jackappsdev.leetcode.presentation.theme.spacingXl
import com.jackappsdev.leetcode.presentation.theme.spacingXs
import com.jackappsdev.leetcode.presentation.theme.teal
import com.jackappsdev.leetcode.presentation.theme.yellow
import com.jackappsdev.leetcode.utils.toCommaString
import io.github.alexzhirkevich.cupertino.adaptive.icons.AdaptiveIcons
import io.github.alexzhirkevich.cupertino.adaptive.icons.Info
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.label_easy
import leetcode.composeapp.generated.resources.label_hard
import leetcode.composeapp.generated.resources.label_medium
import leetcode.composeapp.generated.resources.title_statistics
import org.jetbrains.compose.resources.stringResource

@Composable
fun StatisticsCard(
    data: LeetCodeProfile,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(spacingLg.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacingXs.dp)
            ) {
                Icon(
                    imageVector = AdaptiveIcons.Outlined.Info,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Text(
                    text = stringResource(Res.string.title_statistics),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(spacingLg.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(IntrinsicSize.Min)
            ) {
                DonutChart(
                    total = data.totalQuestions.coerceAtLeast(1),
                    solved = data.totalSolved,
                    easy = data.easySolved,
                    totalEasy = data.totalEasy,
                    medium = data.mediumSolved,
                    totalMedium = data.totalMedium,
                    hard = data.hardSolved,
                    totalHard = data.totalHard,
                    modifier = Modifier.size(130.dp)
                )

                Spacer(modifier = Modifier.width(spacingXl.dp))
                VerticalDivider()
                Spacer(modifier = Modifier.width(spacingXl.dp))

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(spacingMd.dp)
                ) {
                    StatRow(
                        label = stringResource(Res.string.label_easy),
                        value = data.easySolved,
                        total = data.totalEasy,
                        color = teal
                    )
                    StatRow(
                        label = stringResource(Res.string.label_medium),
                        value = data.mediumSolved,
                        total = data.totalMedium,
                        color = yellow
                    )
                    StatRow(
                        label = stringResource(Res.string.label_hard),
                        value = data.hardSolved,
                        total = data.totalHard,
                        color = red
                    )
                }
            }
        }
    }
}

@Composable
private fun StatRow(
    label: String,
    value: Int,
    total: Int,
    color: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = color, style = MaterialTheme.typography.titleMedium)
        Row {
            Spacer(modifier = Modifier.width(spacingLg.dp))
            Text(
                text = value.toCommaString(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = " / ${total.toCommaString()}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DonutChart(
    total: Int,
    solved: Int,
    easy: Int,
    totalEasy: Int,
    medium: Int,
    totalMedium: Int,
    hard: Int,
    totalHard: Int,
    modifier: Modifier = Modifier
) {
    // We draw a 270-degree ring, split into 3 segments with gaps
    val sweepTotal = 270f
    val startAngle = 135f
    val gap = 24f
    val segmentSweep = (sweepTotal - 2 * gap) / 3f

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val stroke = spacingLg.dp.toPx()
            val arcSize = Size(size.width, size.height)

            fun drawSegment(baseStart: Float, progress: Float, color: Color) {
                // background of the segment (incomplete part color, low opacity)
                drawArc(
                    color = color.copy(alpha = 0.25f),
                    startAngle = baseStart,
                    sweepAngle = segmentSweep,
                    useCenter = false,
                    size = arcSize,
                    style = Stroke(width = stroke, cap = StrokeCap.Round)
                )
                // progress within the segment
                val sweep = (progress.coerceIn(0f, 1f)) * segmentSweep
                if (sweep > 0f) {
                    drawArc(
                        color = color,
                        startAngle = baseStart,
                        sweepAngle = sweep,
                        useCenter = false,
                        size = arcSize,
                        style = Stroke(width = stroke, cap = StrokeCap.Round)
                    )
                }
            }

            var cursor = startAngle
            val easyProgress = if (totalEasy <= 0) 0f else easy.toFloat() / totalEasy.toFloat()
            drawSegment(cursor, easyProgress, teal)
            cursor += segmentSweep + gap

            val medProgress = if (totalMedium <= 0) 0f else medium.toFloat() / totalMedium.toFloat()
            drawSegment(cursor, medProgress, yellow)
            cursor += segmentSweep + gap

            val hardProgress = if (totalHard <= 0) 0f else hard.toFloat() / totalHard.toFloat()
            drawSegment(cursor, hardProgress, red)
        }

        // Center label
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = solved.toCommaString(), style = MaterialTheme.typography.titleLarge)
            Text(text = "/${total.toCommaString()}", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
