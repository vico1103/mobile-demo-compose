package cz.vican.scratchcard.library.activate.di

import cz.vican.scratchcard.library.activate.domain.ActivateUseCase
import org.koin.dsl.module

val activateLibraryModule = module {
    factory { ActivateUseCase(get()) }
}