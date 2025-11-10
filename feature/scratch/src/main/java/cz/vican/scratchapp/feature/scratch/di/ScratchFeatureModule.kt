package cz.vican.scratchapp.feature.scratch.di

import cz.vican.scratchapp.feature.scratch.domain.ScratchUseCase
import cz.vican.scratchapp.feature.scratch.presentation.ScratchViewModel
import cz.vican.scratchcard.generic.scratchcard.di.genericScratchCardModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val scratchFeatureModule = module {
    includes(genericScratchCardModule)
    factory { ScratchUseCase() }
    viewModel { ScratchViewModel(get(), get(), get(), get()) }
}