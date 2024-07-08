import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import lolstrategy.di.provideDesktopGoogleeAuthModule
import lolstrategy.di.provideRepositoryModule
import lolstrategy.di.provideUseCaseModule
import lolstrategy.di.providehttpClientModule
import lolstrategy.domain.usecase.GeminiUseCase
import lolstrategy.navigation.NavigationComponentImpl
import lolstrategy.ui.RootApplication
import org.koin.core.context.GlobalContext
import org.koin.java.KoinJavaComponent.getKoin

fun main() = application {

    GlobalContext.startKoin {
        modules(
            providehttpClientModule,
            provideRepositoryModule,
            provideUseCaseModule,
            provideDesktopGoogleeAuthModule
        )
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "lolStrategy"
    ) {
        val getGeminiUseCase = getKoin().get<GeminiUseCase>(GeminiUseCase::class)

        val root = remember {
            NavigationComponentImpl(
                componentContext = DefaultComponentContext(LifecycleRegistry()),
                getGeminiUseCase = getGeminiUseCase
            )
        }

        RootApplication(root)
    }
}
