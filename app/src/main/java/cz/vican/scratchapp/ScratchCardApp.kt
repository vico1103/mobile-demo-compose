package cz.vican.scratchapp

import android.app.Application
import cz.vican.scratchapp.feature.activate.di.activateFeatureModule
import cz.vican.scratchapp.scratch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ScratchCardApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ScratchCardApp)
            viewModelModule
            activateFeatureModule
        }
    }
}