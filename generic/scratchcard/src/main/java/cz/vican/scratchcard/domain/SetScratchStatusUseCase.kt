package cz.vican.scratchcard.domain

import cz.vican.scratchcard.model.ScratchCardStatus

class SetScratchStatusUseCase(private val scratchCardRepository: ScratchCardRepository) {
    fun invoke(status: ScratchCardStatus) {
        scratchCardRepository.setScratchCardStatus(status)
    }
}