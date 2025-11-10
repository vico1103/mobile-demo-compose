package cz.vican.scratchapp.di

import cz.vican.scratchapp.device.GlobalNavigationController
import cz.vican.scratchapp.feature.main.domain.MainNavigationController
import cz.vican.scratchapp.system.NavigationDelegate
import org.koin.dsl.module

val appModule = module {
    single { GlobalNavigationController() }
    factory<MainNavigationController> { get<GlobalNavigationController>() }
    factory { NavigationDelegate(get()) }
}