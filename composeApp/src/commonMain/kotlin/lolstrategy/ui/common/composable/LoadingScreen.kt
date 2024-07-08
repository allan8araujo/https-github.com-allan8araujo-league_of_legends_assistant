package lolstrategy.ui.common.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import lolstrategy.ui.screens.matchupanalyzer.MatchupAnalyzerComponent

@Composable
internal fun LoadingScreen(component: MatchupAnalyzerComponent) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardBackButton(
            goBack = { component.goBack() }
        )
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
