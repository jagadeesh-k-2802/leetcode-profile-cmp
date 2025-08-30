package com.jackappsdev.leetcode.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.presentation.theme.greenLevel1
import com.jackappsdev.leetcode.presentation.theme.greenLevel2
import com.jackappsdev.leetcode.presentation.theme.greenLevel3
import com.jackappsdev.leetcode.presentation.theme.greenLevel4
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import com.jackappsdev.leetcode.presentation.theme.spacingXs
import com.jackappsdev.leetcode.presentation.theme.spacingSm
import com.jackappsdev.leetcode.presentation.theme.spacingXss
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.month_apr
import leetcode.composeapp.generated.resources.month_aug
import leetcode.composeapp.generated.resources.month_dec
import leetcode.composeapp.generated.resources.month_feb
import leetcode.composeapp.generated.resources.month_jan
import leetcode.composeapp.generated.resources.month_jul
import leetcode.composeapp.generated.resources.month_jun
import leetcode.composeapp.generated.resources.month_mar
import leetcode.composeapp.generated.resources.month_may
import leetcode.composeapp.generated.resources.month_nov
import leetcode.composeapp.generated.resources.month_oct
import leetcode.composeapp.generated.resources.month_sep
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Composable
fun ContributionsGraph(
    submissionCalendar: Map<String, Int>,
    modifier: Modifier = Modifier
) {
    val groups = groupByMonth(submissionCalendar)
    val scrollState = rememberScrollState(Int.MAX_VALUE)
    Row(
        modifier = modifier.horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(spacingLg.dp)
    ) {
        groups.forEach { data ->
            MonthColumn(label = data.label, values = data.levels)
        }
    }
}

@Composable
private fun MonthColumn(label: String, values: List<Int?>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.titleSmall, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(spacingSm.dp))
        val rows = 7
        val cols = (values.size + rows - 1) / rows
        Row(horizontalArrangement = Arrangement.spacedBy(spacingXs.dp)) {
            repeat(cols) { c ->
                Column(verticalArrangement = Arrangement.spacedBy(spacingXs.dp)) {
                    repeat(rows) { r ->
                        val idx = c * rows + r
                        if (idx < values.size) {
                            ContributionCell(level = values[idx])
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ContributionCell(level: Int?) {
    val color = when (level) {
        null -> MaterialTheme.colorScheme.surface.copy(alpha = 0f)
        0 -> MaterialTheme.colorScheme.surfaceVariant
        1 -> greenLevel1
        2 -> greenLevel2
        3 -> greenLevel3
        else -> greenLevel4
    }
    Box(
        modifier = Modifier
            .size(14.dp)
            .clip(RoundedCornerShape(spacingXss.dp))
            .background(color)
    )
}

private data class YMD(val year: Int, val month: Int, val day: Int)
private data class MonthKey(val year: Int, val month: Int)
private data class MonthData(val label: String, val levels: List<Int?>)

@Composable
@OptIn(ExperimentalTime::class)
private fun groupByMonth(map: Map<String, Int>): List<MonthData> {
    if (map.isEmpty()) return emptyList()

    val parsed = map.entries
        .mapNotNull { (k, v) ->
            val sec = k.toLongOrNull() ?: return@mapNotNull null
            val dt = Instant.fromEpochSeconds(sec).toLocalDateTime(TimeZone.UTC)
            YMD(dt.year, dt.month.number, dt.day) to v
        }
        .sortedWith(compareBy({ it.first.year }, { it.first.month }, { it.first.day }))

    if (parsed.isEmpty()) return emptyList()

    // Anchor to today; render last 12 months up to current month
    val todayLdt = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    val months = ArrayList<MonthKey>(12)
    var y = todayLdt.year
    var m = todayLdt.month.number
    repeat(12) {
        months.add(MonthKey(y, m))
        m -= 1
        if (m == 0) { m = 12; y -= 1 }
    }
    months.reverse()

    val monthToLevels = LinkedHashMap<MonthKey, IntArray>(12)
    for (mk in months) {
        monthToLevels[mk] = IntArray(daysInMonth(mk.year, mk.month))
    }

    for ((ymd, count) in parsed) {
        val mk = MonthKey(ymd.year, ymd.month)
        val arr = monthToLevels[mk] ?: continue
        val level = when {
            count <= 0 -> 0
            count < 2 -> 1
            count < 5 -> 2
            count < 10 -> 3
            else -> 4
        }
        val idx = (ymd.day - 1).coerceIn(0, arr.lastIndex)
        arr[idx] = level
    }

    return months.map { mk ->
        val label = monthLabel(mk.month)
        var levels = monthToLevels[mk]!!.toList()
        // Trim future days for current month
        if (mk.year == todayLdt.year && mk.month == todayLdt.month.number) {
            levels = levels.take(todayLdt.day)
        }
        // Compute offset so the first day of month starts at correct weekday position (Sun-first)
        val firstDow = LocalDate(mk.year, mk.month, 1).dayOfWeek
        val sundayFirstIndex = ((firstDow.ordinal + 1) % 7)
        val padded: List<Int?> = List(sundayFirstIndex) { null } + levels.map { it }
        MonthData(label = label, levels = padded)
    }
}

@Composable
private fun monthLabel(month: Int): String = when (month) {
    1 -> stringResource(Res.string.month_jan)
    2 -> stringResource(Res.string.month_feb)
    3 -> stringResource(Res.string.month_mar)
    4 -> stringResource(Res.string.month_apr)
    5 -> stringResource(Res.string.month_may)
    6 -> stringResource(Res.string.month_jun)
    7 -> stringResource(Res.string.month_jul)
    8 -> stringResource(Res.string.month_aug)
    9 -> stringResource(Res.string.month_sep)
    10 -> stringResource(Res.string.month_oct)
    11 -> stringResource(Res.string.month_nov)
    12 -> stringResource(Res.string.month_dec)
    else -> month.toString()
}

private fun daysInMonth(year: Int, month: Int): Int {
    return when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> if (isLeapYear(year)) 29 else 28
        else -> 30
    }
}

private fun isLeapYear(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}
