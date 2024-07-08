package lolstrategy.di

import MockGoogleAuthRepositoryImpl
import lolstrategy.domain.repository.GoogleAuthRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val provideDesktopGoogleeAuthModule = module {
    factory {
        MockGoogleAuthRepositoryImpl.GoogleAuthUiRepositoryMocked
    }
    factoryOf(::MockGoogleAuthRepositoryImpl) bind GoogleAuthRepository::class
}

internal actual val platformModule: Module = module {
    includes(provideDesktopGoogleeAuthModule)
}
