package cz.vican.scratchcard.domain

class ObserveScratchStatusUseCase(private val repository: ScratchCardRepository) {

    fun invoke() = repository.observeScratchCardStatus()
}