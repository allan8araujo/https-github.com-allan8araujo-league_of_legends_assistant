package lolstrategy.domain.repository

import androidx.compose.runtime.Composable

interface GoogleAuthRepository {
    @Composable
    fun getUiProvider(): GoogleAuthUiRepository

    suspend fun signOut()
}
