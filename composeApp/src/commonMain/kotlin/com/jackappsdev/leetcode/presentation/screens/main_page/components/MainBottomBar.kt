package com.jackappsdev.leetcode.presentation.screens.main_page.components

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jackappsdev.leetcode.presentation.navigation.Routes
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveNavigationBar
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveNavigationBarItem
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import io.github.alexzhirkevich.cupertino.adaptive.icons.AdaptiveIcons
import io.github.alexzhirkevich.cupertino.adaptive.icons.Home
import io.github.alexzhirkevich.cupertino.adaptive.icons.Person
import io.github.alexzhirkevich.cupertino.adaptive.icons.Search
import leetcode.composeapp.generated.resources.Res
import leetcode.composeapp.generated.resources.text_friends
import leetcode.composeapp.generated.resources.text_home
import leetcode.composeapp.generated.resources.text_search
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun MainBottomBar(
    currentRoute: Routes,
    onNavigate: (Routes) -> Unit,
) {
    AdaptiveNavigationBar {
        AdaptiveNavigationBarItem(
            selected = currentRoute == Routes.Home,
            onClick = { onNavigate(Routes.Home) },
            label = { Text(stringResource(Res.string.text_home)) },
            icon = { Icon(imageVector = AdaptiveIcons.Outlined.Home, contentDescription = null) }
        )
        AdaptiveNavigationBarItem(
            selected = currentRoute == Routes.Search,
            onClick = { onNavigate(Routes.Search) },
            label = { Text(stringResource(Res.string.text_search)) },
            icon = { Icon(imageVector = AdaptiveIcons.Outlined.Search, contentDescription = null) }
        )
        AdaptiveNavigationBarItem(
            selected = currentRoute == Routes.Friends,
            onClick = { onNavigate(Routes.Friends) },
            label = { Text(stringResource(Res.string.text_friends)) },
            icon = { Icon(imageVector = AdaptiveIcons.Outlined.Person, contentDescription = null) }
        )
    }
}
