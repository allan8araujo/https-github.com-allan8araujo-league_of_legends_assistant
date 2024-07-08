package lolstrategy.di

import androidx.credentials.CredentialManager
import lolstrategy.domain.repository.GoogleAuthRepository
import lolstrategy.repository.GoogleAuthRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val googleeAndroidAuthModule = module {

    factory { CredentialManager.create(androidContext()) } bind CredentialManager::class
    factoryOf(::GoogleAuthRepositoryImpl) bind GoogleAuthRepository::class
}

internal actual val platformModule: Module = module {
    includes(googleeAndroidAuthModule)
}
