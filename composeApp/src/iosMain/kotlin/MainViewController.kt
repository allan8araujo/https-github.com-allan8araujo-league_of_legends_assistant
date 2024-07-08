import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import lolstrategy.navigation.NavigationComponentImpl
import lolstrategy.ui.RootApplication

fun MainViewController() = ComposeUIViewController {
    val root = remember {
        NavigationComponentImpl(DefaultComponentContext(LifecycleRegistry()))
    }

    RootApplication(root)
}
