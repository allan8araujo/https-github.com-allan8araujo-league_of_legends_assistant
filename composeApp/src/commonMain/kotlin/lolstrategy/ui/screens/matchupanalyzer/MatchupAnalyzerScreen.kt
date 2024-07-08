package lolstrategy.ui.screens.matchupanalyzer

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import lolstrategy.domain.models.GeminiState
import lolstrategy.ui.common.composable.LoadingScreen
import lolstrategy.ui.common.composable.StandardBackButton
import lolstrategy.ui.common.toComposableText

@Composable
fun MatchupAnalyzerScreen(
    component: MatchupAnalyzerComponent
) {

    val coroutineScope = rememberCoroutineScope()

    val verticalScroll = rememberScrollState()

    val content = component.matchupAnalyzerState.collectAsState()

    when (val state = content.value) {
        is GeminiState.Error -> LoadingScreen(component = component)
        GeminiState.Loading -> LoadingScreen(component = component)
        is GeminiState.Success -> MatchupAnalyzerScreen(
            verticalScroll = verticalScroll,
            content = state.content,
            component = component
        )
    }

    LaunchedEffect(Unit) {

        coroutineScope.launch {

            component.getAnalyzeMatchup()
        }
    }
}


@Composable
fun MatchupAnalyzerScreen(
    verticalScroll: ScrollState,
    content: String,
    component: MatchupAnalyzerComponent
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(verticalScroll)
            .padding(horizontal = 24.dp),
    ) {

        StandardBackButton(goBack = { component.goBack() })

        Text(
            fontSize = 32.sp,
            text = "${component.userChampion.value} -> ${component.enemyChampion.value}",
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            fontSize = 16.sp,
            text = content
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                component.goBack()
            }) {
            Text("see other matchup")
        }
    }
}
