import androidx.compose.runtime.Composable
import lolstrategy.data.GoogleUser
import lolstrategy.domain.repository.GoogleAuthRepository
import lolstrategy.domain.repository.GoogleAuthUiRepository

internal class MockGoogleAuthRepositoryImpl() : GoogleAuthRepository {

    object GoogleAuthUiRepositoryMocked : GoogleAuthUiRepository {
        override suspend fun signIn(): GoogleUser? = null
    }

    @Composable
    override fun getUiProvider(): GoogleAuthUiRepository = GoogleAuthUiRepositoryMocked

    override suspend fun signOut() = Unit
}
