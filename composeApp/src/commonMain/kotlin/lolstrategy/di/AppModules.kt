package lolstrategy.di

import org.koin.core.module.Module


internal expect val platformModule: Module
val appModules: List<Module> get() = providehttpClientModule + provideRepositoryModule + provideUseCaseModule + googleAuthModule + platformModule
