package cz.vican.scratchapp.di

import cz.vican.scratchapp.device.GlobalNavigationController
import cz.vican.scratchapp.feature.activate.domain.ActivateNavigationController
import cz.vican.scratchapp.feature.main.domain.MainNavigationController
import cz.vican.scratchapp.feature.scratch.domain.ScratchNavigationController
import cz.vican.scratchapp.system.NavigationDelegate
import org.koin.dsl.binds
import org.koin.dsl.module

val appModule = module {
    single { GlobalNavigationController() }.binds(
        classes = arrayOf(
            MainNavigationController::class,
            ScratchNavigationController::class,
            ActivateNavigationController::class
        )
    )

    factory { NavigationDelegate(get()) }
}