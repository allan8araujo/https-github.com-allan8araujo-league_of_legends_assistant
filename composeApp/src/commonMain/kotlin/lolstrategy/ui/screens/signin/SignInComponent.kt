package lolstrategy.ui.screens.signin

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import lolstrategy.data.GoogleUser

class SignInComponent(
    private val GoToHomeScreen: () -> Unit,
    componentContext: ComponentContext,
) {

    var signedInUser: MutableStateFlow<GoogleUser?> = MutableStateFlow(null)

    fun onEvent(event: ScreenSignInEvent) {
        when (event) {
            ScreenSignInEvent.GoToHomeScreen -> {
                GoToHomeScreen()
            }

            is ScreenSignInEvent.UpdateSignedInUser -> {
                signedInUser.value = event.googleUser
            }
        }
    }
}
