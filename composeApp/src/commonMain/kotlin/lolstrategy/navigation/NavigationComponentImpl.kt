package lolstrategy.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import lolstrategy.domain.usecase.GeminiUseCase
import lolstrategy.ui.common.MatchupState
import lolstrategy.ui.screens.history.HistoryComponent
import lolstrategy.ui.screens.home.HomeComponent
import lolstrategy.ui.screens.signin.SignInComponent
import lolstrategy.ui.screens.matchupanalyzer.MatchupAnalyzerComponent
import lolstrategy.ui.screens.settings.SettingsComponent

class NavigationComponentImpl(
    componentContext: ComponentContext,
    private val getGeminiUseCase: GeminiUseCase,
) : NavigationComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.SignInScreen,
        handleBackButton = true,
        childFactory = ::createChild,
        key = "authStack",
    )

    private fun createChild(
        config: Configuration,
        componentContext: ComponentContext,
    ): NavigationComponent.Screens {

        return when (config) {
            is Configuration.HomeScreen -> NavigationComponent.Screens.BottomNavigation.Main(
                HomeComponent(
                    componentContext = componentContext,
                    goToMatchupAnalyzer = { matchupState ->
                        openMatchupAnalyzer(matchupState = matchupState)
                    },
                    goBack = { navigation.pop() },
                )
            )

            is Configuration.HistoryScreen -> NavigationComponent.Screens.BottomNavigation.History(
                HistoryComponent(
                    componentContext = componentContext,
                )
            )

            is Configuration.SettingsScreen -> NavigationComponent.Screens.BottomNavigation.Settings(
                SettingsComponent(
                    componentContext = componentContext,
                )
            )


            is Configuration.SignInScreen -> NavigationComponent.Screens.SignInScreen.Main(
                SignInComponent(
                    GoToHomeScreen = { openHome() },
                    componentContext = componentContext,
                )
            )

            is Configuration.MatchupAnalyzerScreen -> NavigationComponent.Screens.BottomNavigation.MatchupAnalyzer(
                MatchupAnalyzerComponent(
                    componentContext = componentContext,
                    goBack = {
                        navigation.pop()
                    },
                    goToMatchupAnalyzer = {},
                    getGeminiUseCase = getGeminiUseCase,
                    matchupState = config.matchupState
                )
            )
        }
    }

    @Serializable
    sealed class Configuration {

        data object HomeScreen : Configuration()

        data object HistoryScreen : Configuration()

        data object SettingsScreen : Configuration()

        data object SignInScreen : Configuration()

        data class MatchupAnalyzerScreen(
            val matchupState: MatchupState
        ) : Configuration()
    }

    override val childStackBottom: Value<ChildStack<*, NavigationComponent.Screens>> =
        childStack

    override fun openHome() = navigation.bringToFront(Configuration.HomeScreen)

    override fun openHistory() = navigation.bringToFront(Configuration.HistoryScreen)

    override fun openSettings() = navigation.bringToFront(Configuration.SettingsScreen)

    @OptIn(ExperimentalDecomposeApi::class)
    override fun openMatchupAnalyzer(matchupState: MatchupState) =
        navigation.pushNew(
            Configuration.MatchupAnalyzerScreen(
                matchupState = matchupState
            )
        )

    override fun openSignIn() = navigation.bringToFront(Configuration.SettingsScreen)

    override fun goBack() = navigation.pop()
}
