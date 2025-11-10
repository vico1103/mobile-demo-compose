package cz.vican.scratchapp.feature.main.di

import cz.vican.scratchapp.feature.main.presentation.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainFeatureModule = module {
    viewModel { MainViewModel(get()) }
}