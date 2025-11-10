package cz.vican.scratchapp

import android.app.Application
import cz.vican.scratchapp.di.appModule
import cz.vican.scratchapp.feature.main.di.mainFeatureModule
import cz.vican.scratchapp.feature.scratch.di.scratchFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ScratchApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ScratchApp)
            modules(
                modules = listOf(
                    appModule,
                    mainFeatureModule,
                    scratchFeatureModule
                )
            )
        }
    }
}