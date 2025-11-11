package cz.vican.scratchapp.feature.activate.di

import cz.vican.scratchapp.feature.activate.api.ActivateApi
import cz.vican.scratchapp.feature.activate.device.RemoteActivateRepository
import cz.vican.scratchapp.feature.activate.domain.ActivateRepository
import cz.vican.scratchapp.feature.activate.domain.ActivateUseCase
import cz.vican.scratchapp.feature.activate.presentation.ActivateViewModel
import cz.vican.scratchapp.library.networking.Retrofit
import cz.vican.scratchapp.library.networking.di.networkModule
import cz.vican.scratchcard.generic.scratchcard.di.genericScratchCardModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val activateFeatureModule = module {
    includes(genericScratchCardModule)
    includes(networkModule)
    single {
        Retrofit.createWebService<ActivateApi>(
            get(),
            get(),
            "https://api.o2.sk/"
        )
    }
    factory<ActivateRepository> { RemoteActivateRepository(get()) }
    factory { ActivateUseCase(get()) }
    viewModel { ActivateViewModel(get(), get(), get(), get(), get()) }
}