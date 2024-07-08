package lolstrategy.di

import lolstrategy.data.repository.GeminiRepositoryImpl
import lolstrategy.domain.repository.GeminiRepository
import org.koin.dsl.module

val provideRepositoryModule = module {
    single<GeminiRepository> {
        GeminiRepositoryImpl(get())
    }
}
