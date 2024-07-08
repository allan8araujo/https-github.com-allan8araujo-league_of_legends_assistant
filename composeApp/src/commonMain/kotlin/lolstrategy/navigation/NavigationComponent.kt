package lolstrategy.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import lolstrategy.ui.common.MatchupState
import lolstrategy.ui.screens.history.HistoryComponent
import lolstrategy.ui.screens.home.HomeComponent
import lolstrategy.ui.screens.signin.SignInComponent
import lolstrategy.ui.screens.matchupanalyzer.MatchupAnalyzerComponent
import lolstrategy.ui.screens.settings.SettingsComponent

interface NavigationComponent {

    val childStackBottom: Value<ChildStack<*, Screens>>

    /**
     * Bottom navigation bar
     */
    fun openHome()

    fun openHistory()

    fun openSettings()

    /**
     * Others types of navigation
     */

    fun openMatchupAnalyzer(matchupState: MatchupState)

    fun openSignIn()

    fun goBack()

    sealed class Screens {

        sealed class SignInScreen(val component: SignInComponent) : Screens() {

            data class Main(val component: SignInComponent) : Screens()
        }

        sealed class BottomNavigation : Screens() {

            data class Main(val component: HomeComponent) : BottomNavigation()
            data class History(val component: HistoryComponent) : BottomNavigation()
            data class Settings(val component: SettingsComponent) : BottomNavigation()
            data class MatchupAnalyzer(val component: MatchupAnalyzerComponent) : Screens()
        }
    }
}
