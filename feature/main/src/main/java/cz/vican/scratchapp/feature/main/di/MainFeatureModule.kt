package cz.vican.scratchapp.feature.main.di

import cz.vican.scratchapp.feature.main.presentation.MainViewModel
import cz.vican.scratchcard.generic.scratchcard.di.genericScratchCardModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainFeatureModule = module {
    includes(genericScratchCardModule)
    viewModel { MainViewModel(get(), get()) }
}