package lolstrategy.ui.screens.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import lolstrategy.data.GoogleUser
import lolstrategy.domain.repository.GoogleAuthRepository
import lolstrategy.getPlatform
import lolstrategy.ui.common.GoogleButtonUiContainer
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@Composable
fun SignInScreen(
    component: SignInComponent
) {

    val signedInUser = component.signedInUser.collectAsState()

    val googleAuthRepository = koinInject<GoogleAuthRepository>()
    val coroutineScope = rememberCoroutineScope()

    SignInScreen(
        signedInUser = signedInUser,
        setSignedInUser = { googleUser ->
            component.onEvent(
                ScreenSignInEvent.UpdateSignedInUser(
                    googleUser
                )
            )
        },
        googleAuthRepository = googleAuthRepository,
        coroutineScope = coroutineScope,
        goToHome = {
            component.onEvent(ScreenSignInEvent.GoToHomeScreen)
        }
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    signedInUser: State<GoogleUser?>,
    googleAuthRepository: GoogleAuthRepository,
    coroutineScope: CoroutineScope,
    setSignedInUser: (googleUser: GoogleUser?) -> Unit,
    goToHome: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome back!",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(text = "You've been missed.")

        if (getPlatform().name == "Jvm version" || signedInUser.value != null)
            goToHome()
        else {
            GoogleButtonUiContainer(onGoogleSignInResult = { googleUser ->
                coroutineScope.launch {
                    setSignedInUser(googleUser)
                }

            }) {
                Button(
                    onClick = { this.onClick() },
                ) {

                    Image(
                        painterResource("icons8-google.xml"),
                        contentDescription = "SignIn with Google"
                    )
                    Text("SignIn with Google")
                }
            }
        }
    }
}