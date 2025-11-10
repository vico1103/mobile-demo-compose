package cz.vican.scratchcard.generic.scratchcard.domain

import cz.vican.scratchcard.generic.scratchcard.model.ScratchCardStatus

class LoadScratchStatusUseCase(
    private val scratchCardRepository: ScratchCardRepository
) {
    fun invoke(): ScratchCardStatus {
        return scratchCardRepository.getScratchCardStatus()
    }
}