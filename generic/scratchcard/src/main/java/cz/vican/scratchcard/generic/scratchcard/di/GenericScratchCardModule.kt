package cz.vican.scratchcard.generic.scratchcard.di

import cz.vican.scratchcard.generic.scratchcard.device.InMemoryScratchCardRepository
import cz.vican.scratchcard.generic.scratchcard.domain.LoadScratchStatusUseCase
import cz.vican.scratchcard.generic.scratchcard.domain.ObserveScratchStatusUseCase
import cz.vican.scratchcard.generic.scratchcard.domain.ScratchCardRepository
import cz.vican.scratchcard.generic.scratchcard.domain.SetScratchStatusUseCase
import org.koin.dsl.module

val genericScratchCardModule = module {
    single<ScratchCardRepository> { InMemoryScratchCardRepository() }
    factory { LoadScratchStatusUseCase(get()) }
    factory { SetScratchStatusUseCase(get()) }
    factory { ObserveScratchStatusUseCase(get()) }
}