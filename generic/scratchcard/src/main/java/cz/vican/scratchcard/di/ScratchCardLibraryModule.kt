package cz.vican.scratchcard.di

import cz.vican.scratchcard.device.InMemoryScratchCardRepository
import cz.vican.scratchcard.domain.LoadScratchStatusUseCase
import cz.vican.scratchcard.domain.ObserveScratchStatusUseCase
import cz.vican.scratchcard.domain.ScratchCardRepository
import cz.vican.scratchcard.domain.SetScratchStatusUseCase
import org.koin.dsl.module

val scratchCardLibraryModule = module {
    single<ScratchCardRepository> { InMemoryScratchCardRepository() }
    factory { LoadScratchStatusUseCase(get()) }
    factory { SetScratchStatusUseCase(get()) }
    factory { ObserveScratchStatusUseCase(get ()) }
}