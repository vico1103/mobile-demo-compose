package cz.vican.scratchapp.scratch.di

import cz.vican.scratchapp.scratch.presentation.ScratchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ScratchViewModel() }
}