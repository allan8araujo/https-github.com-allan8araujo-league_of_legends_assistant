package lolstrategy.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import lolstrategy.navigation.NavigationComponent.Screens
import lolstrategy.navigation.NavigationComponentImpl
import lolstrategy.ui.screens.history.HistoryScreen
import lolstrategy.ui.screens.home.HomeScreen
import lolstrategy.ui.screens.signin.SignInScreen
import lolstrategy.ui.screens.matchupanalyzer.MatchupAnalyzerScreen
import lolstrategy.ui.screens.settings.SettingsScreen

@Composable
fun RootApplication(component: NavigationComponentImpl) {
    MaterialTheme {

        val childStack by component.childStack.subscribeAsState()

        val selectedIndex = rememberSaveable { mutableStateOf(0) }

        val navigationItems = navigationItems(component = component)

        val bottomNavigationVisibility =
            childStack.active.instance is Screens.BottomNavigation

        Scaffold(
            bottomBar = {
                AnimatedVisibility(bottomNavigationVisibility) {
                    BottomNavigationComponent(
                        navigationItems = navigationItems,
                        selectedIndex = selectedIndex
                    )
                }
            },
            content = { _ ->
                Children(
                    stack = childStack,
                    animation = stackAnimation(slide())
                ) { child ->
                    when (val instance: Screens = child.instance) {

                        is Screens.BottomNavigation.Main -> HomeScreen(instance.component)
                        is Screens.BottomNavigation.History -> HistoryScreen(instance.component)
                        is Screens.BottomNavigation.Settings -> SettingsScreen(instance.component)
                        is Screens.BottomNavigation.MatchupAnalyzer -> MatchupAnalyzerScreen(instance.component)
                        is Screens.SignInScreen.Main -> SignInScreen(instance.component)
                    }
                }
            }
        )
    }
}

@Composable
private fun BottomNavigationComponent(
    selectedIndex: MutableState<Int>,
    navigationItems: List<NavigationItem>
) {
    BottomNavigation {
        navigationItems.forEachIndexed { index, item ->
            val isSelected = selectedIndex.value == index

            BottomNavigationItem(
                selected = isSelected,
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selected else item.unselected,
                        contentDescription = null
                    )
                },
                alwaysShowLabel = false,
                label = {
                    Text(text = item.title)
                },
                onClick = {
                    selectedIndex.value = index
                    item.openScreen()
                }
            )
        }
    }
}

private fun navigationItems(component: NavigationComponentImpl) = listOf(

    NavigationItem(
        title = "home",
        openScreen = component::openHome,
        selected = Icons.Filled.Home,
        unselected = Icons.Outlined.Home,
    ),
    NavigationItem(
        title = "history",
        openScreen = component::openHistory,
        selected = Icons.Filled.List,
        unselected = Icons.Outlined.List,
    ),
    NavigationItem(
        title = "settings",
        openScreen = component::openSettings,
        selected = Icons.Filled.Settings,
        unselected = Icons.Outlined.Settings,
    ),
)

data class NavigationItem(
    val title: String,
    val openScreen: () -> Unit,
    val selected: ImageVector,
    val unselected: ImageVector
)
