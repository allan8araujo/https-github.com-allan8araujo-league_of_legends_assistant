package lolstrategy.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

object AppInitializer {

    fun initialize(
        onKoinStart: KoinApplication.() -> Unit
    ) {
        setupKoin(onKoinStart)
    }

    private fun setupKoin(onKoinStart: KoinApplication.() -> Unit) {
        startKoin {
            onKoinStart()
            modules(appModules)
        }
    }
}
