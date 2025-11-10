package cz.vican.scratchapp.feature.activate.di

import cz.vican.library.networking.Retrofit
import cz.vican.scratchapp.feature.activate.api.ActivateApi
import cz.vican.scratchapp.feature.activate.device.RemoteActivateRepository
import cz.vican.scratchapp.feature.activate.domain.ActivateRepository
import cz.vican.scratchapp.feature.activate.domain.ActivateUseCase
import cz.vican.scratchapp.feature.activate.presentation.ActivateViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val activateFeatureModule = module {
    factory<ActivateRepository> { RemoteActivateRepository(get()) }
    factory { ActivateUseCase(get()) }
    single<ActivateApi> { Retrofit.createWebService<ActivateApi>(get(), get(),"https://api.o2.sk/") }
    viewModel { ActivateViewModel(get(), get(), get(), get()) }
}