package cz.vican.scratchcard.generic.scratchcard.domain

class ObserveScratchStatusUseCase(private val repository: ScratchCardRepository) {

    fun invoke() = repository.observeScratchCardStatus()
}