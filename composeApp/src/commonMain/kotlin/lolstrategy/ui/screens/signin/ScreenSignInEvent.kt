package lolstrategy.ui.screens.signin

import lolstrategy.data.GoogleUser

sealed interface ScreenSignInEvent {
    data object GoToHomeScreen : ScreenSignInEvent
    data class UpdateSignedInUser(val googleUser: GoogleUser?) : ScreenSignInEvent
}
