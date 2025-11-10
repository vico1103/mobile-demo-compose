package cz.vican.scratchcard.generic.scratchcard.domain

import cz.vican.scratchcard.generic.scratchcard.model.ScratchCardStatus

class SetScratchStatusUseCase(private val scratchCardRepository: ScratchCardRepository) {
    fun invoke(status: ScratchCardStatus) {
        scratchCardRepository.setScratchCardStatus(status)
    }
}