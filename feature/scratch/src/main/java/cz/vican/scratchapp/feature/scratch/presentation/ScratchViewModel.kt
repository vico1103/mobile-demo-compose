package cz.vican.scratchapp.feature.scratch.presentation

import androidx.lifecycle.viewModelScope
import cz.vican.scratchapp.feature.scratch.domain.ScratchNavigationController
import cz.vican.scratchapp.feature.scratch.domain.ScratchUseCase
import cz.vican.scratchapp.library.architecture.presentation.AbstractViewModel
import cz.vican.scratchcard.generic.scratchcard.domain.ObserveScratchStatusUseCase
import cz.vican.scratchcard.generic.scratchcard.domain.SetScratchStatusUseCase
import cz.vican.scratchcard.generic.scratchcard.model.ScratchCardStatus
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ScratchViewModel(
    private val navigationController: ScratchNavigationController,
    private val scratchUseCase: ScratchUseCase,
    private val observeScratchStatusUseCase: ObserveScratchStatusUseCase,
    private val setScratchUseCase: SetScratchStatusUseCase
) : AbstractViewModel<ScratchViewModel.UiState>() {

    private var scratchJob: Job? = null

    init {
        observeScratchStatus()
    }

    override fun createInitialState(): UiState {
        return UiState()
    }

    fun onScratchCard() {
       scratchJob = viewModelScope.launch {
            scratchUseCase.scratch().collect { code ->
                setScratchUseCase.invoke(ScratchCardStatus(ScratchCardStatus.Status.REVEALED, code))
            }
        }
    }

    fun onBackButtonClicked() {
        navigationController.navigateBack()
    }

    override fun onCleared() {
        super.onCleared()
        cancelScratch()
    }

    private fun cancelScratch() {
        scratchJob?.cancel()
        scratchJob = null
    }

    private fun observeScratchStatus() {
        viewModelScope.launch {
            observeScratchStatusUseCase.invoke().collect {
                setState { copy(scratchState = it.status.name) }
            }
        }
    }

    data class UiState(
        val title: String = "Scratch Screen",
        val stateTitle: String = "Current Scratch State:",
        val scratchState: String = "",
        val scratchButtonText: String = "Scratch Now"
    ) : IState
}