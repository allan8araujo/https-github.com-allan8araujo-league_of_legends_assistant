package lolstrategy.di

import lolstrategy.data.GoogleAuthCredentials
import org.koin.dsl.module

val googleAuthModule = module {
    factory {
        GoogleAuthCredentials(
            serverId = "99963895611-g8e3r0d010cebee2h77h7mbjmkc3e725.apps.googleusercontent.com",
        )
    }
}
