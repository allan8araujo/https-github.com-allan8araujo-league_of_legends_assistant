package lolstrategy.ui.common

import kotlinx.serialization.Serializable

@Serializable
class MatchupState(
    val userChampion: String,
    val enemyChampion: String,
)
