package lolstrategy.domain.repository

import lolstrategy.data.GoogleUser

interface GoogleAuthUiRepository {

    suspend fun signIn(): GoogleUser?
}
