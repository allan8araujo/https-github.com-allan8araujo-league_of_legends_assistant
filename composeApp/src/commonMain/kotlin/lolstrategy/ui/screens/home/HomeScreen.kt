package lolstrategy.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import lolstrategy.ui.utils.CustomDropdown
import lolstrategy.ui.utils.listOfChampions

@Composable
fun HomeScreen(
    component: HomeComponent
) {
    val listOfChampions = remember { listOfChampions }

    val userChampion by component.userChampion.subscribeAsState()
    val enemyChampion by component.enemyChampion.subscribeAsState()

    val userChampionIsNotEmpty =
        userChampion in listOfChampions && userChampion.isNotEmpty()
    val enemyChampionIsNotEmpty =
        enemyChampion in listOfChampions && enemyChampion.isNotEmpty()

    HomeScreen(
        userChampion = userChampion,
        enemyChampion = enemyChampion,
        listOfChampions = listOfChampions,
        userChampionIsNotEmpty = userChampionIsNotEmpty,
        enemyChampionIsNotEmpty = enemyChampionIsNotEmpty,
        goToMatchupAnalyzerScreen = { component.onEvent(ScreenHomeEvent.GoToMatchupAnalyzerScreen) },
        updateUserChampion = { champion ->
            component.onEvent(ScreenHomeEvent.UpdateUserChampion(champion))
        },
        updateEnemyChampion = { champion ->
            component.onEvent(ScreenHomeEvent.UpdateEnemyChampion(champion))
        }
    )
}

@Composable
private fun HomeScreen(
    userChampion: String,
    enemyChampion: String,
    updateUserChampion: (champion: String) -> Unit = {},
    updateEnemyChampion: (champion: String) -> Unit = {},
    listOfChampions: ArrayList<String>,
    userChampionIsNotEmpty: Boolean,
    enemyChampionIsNotEmpty: Boolean,
    goToMatchupAnalyzerScreen: () -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {

            CustomDropdown(
                champion = userChampion,
                itemsList = listOfChampions,
                fieldName = "your champion",
                updateChampion = { champion ->
                    updateUserChampion(champion)
                }
            )

            this@Column.AnimatedVisibility(
                visible = userChampionIsNotEmpty
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {

                    CustomDropdown(
                        itemsList = listOfChampions,
                        fieldName = "enemy champion",
                        champion = enemyChampion,
                        updateChampion = { champion ->
                            updateEnemyChampion(champion)
                        },
                    )

                    AnimatedVisibility(
                        visible = true
                    ) {

                        Button(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth(),
                            onClick = {
                                goToMatchupAnalyzerScreen()
                            }) {
                            Text("see matchup!")
                        }
                    }
                }
            }
        }
    }
}
