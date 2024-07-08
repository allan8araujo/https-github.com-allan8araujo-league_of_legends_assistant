package lolstrategy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import lolstrategy.domain.usecase.GeminiUseCase
import lolstrategy.navigation.NavigationComponentImpl
import lolstrategy.ui.RootApplication
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    val GeminiUseCase: GeminiUseCase by inject()

    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = retainedComponent { context ->
            NavigationComponentImpl(
                componentContext = context,
                getGeminiUseCase = GeminiUseCase
            )
        }

        setContent {
            RootApplication(component = root)
        }
    }
}
