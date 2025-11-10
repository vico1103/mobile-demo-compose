package cz.vican.scratchcard.domain

import cz.vican.scratchcard.model.ScratchCardStatus

class LoadScratchStatusUseCase(
    private val scratchCardRepository: ScratchCardRepository
) {
    fun invoke(): ScratchCardStatus {
        return scratchCardRepository.getScratchCardStatus()
    }
}