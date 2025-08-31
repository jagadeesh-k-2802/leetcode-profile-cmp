package com.jackappsdev.leetcode.presentation.screens.home_page.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jackappsdev.leetcode.domain.model.LeetCodeProfile
import com.jackappsdev.leetcode.presentation.components.ContributionsGraph
import com.jackappsdev.leetcode.presentation.theme.spacingLg
import com.jackappsdev.leetcode.presentation.theme.spacingMd
import com.jackappsdev.leetcode.presentation.theme.spacingXs
import io.github.alexzhirkevich.cupertino.adaptive.icons.AdaptiveIcons
import io.github.alexzhirkevich.cupertino.adaptive.icons.DateRange
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.title_activity_graph
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ActivityGraphCard(
    modifier: Modifier = Modifier,
    data: LeetCodeProfile,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(modifier = Modifier.padding(spacingLg.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacingXs.dp)
            ) {
                Icon(
                    imageVector = AdaptiveIcons.Outlined.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Text(
                    text = stringResource(Res.string.title_activity_graph),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(spacingMd.dp))
            ContributionsGraph(
                submissionCalendar = data.submissionCalendar,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
