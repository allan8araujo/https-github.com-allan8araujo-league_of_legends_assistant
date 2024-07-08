package lolstrategy.di

import lolstrategy.domain.usecase.GeminiUseCase
import org.koin.dsl.module

val provideUseCaseModule = module {
    single<GeminiUseCase> {
        GeminiUseCase(get())
    }
}
