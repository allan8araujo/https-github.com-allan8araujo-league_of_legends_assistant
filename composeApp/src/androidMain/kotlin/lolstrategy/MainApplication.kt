package lolstrategy

import android.app.Application
import lolstrategy.di.AppInitializer
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppInitializer.initialize {
            androidContext(this@MainApplication)
        }
    }
}
