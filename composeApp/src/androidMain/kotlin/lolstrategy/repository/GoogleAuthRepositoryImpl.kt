package lolstrategy.repository

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import lolstrategy.data.GoogleAuthCredentials
import lolstrategy.di.GoogleAuthUiRepositoryImpl
import lolstrategy.domain.repository.GoogleAuthRepository
import lolstrategy.domain.repository.GoogleAuthUiRepository

internal class GoogleAuthRepositoryImpl(
    private val credentials: GoogleAuthCredentials,
    private val credentialManager: CredentialManager,
) : GoogleAuthRepository {

    @Composable
    override fun getUiProvider(): GoogleAuthUiRepository {
        val activityContext = LocalContext.current
        return GoogleAuthUiRepositoryImpl(
            activityContext = activityContext,
            credentialManager = credentialManager,
            credentials = credentials
        )
    }

    override suspend fun signOut() {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}