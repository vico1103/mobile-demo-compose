package cz.vican.scratchapp.feature.activate.presentation

import androidx.lifecycle.viewModelScope
import cz.vican.scratchapp.feature.activate.domain.ActivateNavigationController
import cz.vican.scratchapp.feature.activate.domain.ActivateUseCase
import cz.vican.scratchapp.feature.activate.presentation.ActivateViewModel.UiState
import cz.vican.scratchapp.library.architecture.presentation.AbstractViewModel
import cz.vican.scratchcard.generic.scratchcard.domain.LoadScratchStatusUseCase
import cz.vican.scratchcard.generic.scratchcard.domain.ObserveScratchStatusUseCase
import cz.vican.scratchcard.generic.scratchcard.domain.SetScratchStatusUseCase
import cz.vican.scratchcard.generic.scratchcard.model.ScratchCardStatus
import cz.vican.scratchcard.generic.scratchcard.model.ScratchCardStatus.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class ActivateViewModel(
    private val activateNavigationController: ActivateNavigationController,
    private val activateUseCase: ActivateUseCase,
    private val observeScratchStatusUseCase: ObserveScratchStatusUseCase,
    private val loadScratchStatusUseCase: LoadScratchStatusUseCase,
    private val setScratchStatusUseCase: SetScratchStatusUseCase
) : AbstractViewModel<UiState>() {

    override fun createInitialState(): UiState {
        return UiState()
    }

    init {
        observeScratchStatus()
    }

    fun onBackClicked() {
        activateNavigationController.navigateBack()
    }

    fun onActivateCard() {
        val scratchCard = loadScratchStatusUseCase.invoke()
        when (scratchCard.status) {
            Status.UNSCRATCHED -> return
            Status.REVEALED, Status.REDEEMED -> activate(scratchCard)
        }
    }

    private fun activate(scratchCardStatus: ScratchCardStatus) {
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            activateUseCase.invoke(scratchCardStatus.code).collect { result ->
                result.onSuccess {
                    if (it.isActivated) {
                        setScratchStatusUseCase.invoke(scratchCardStatus.copy(status = Status.REDEEMED))
                    } else {
                        showError("Activation failed")
                    }
                }.onFailure {
                    showError("Activation error, problem with network. Please try again later.")
                }
            }
        }
    }

    private fun showError(message: String) {
        setState { copy(errorMessage = message) }
    }

    private fun observeScratchStatus() {
        viewModelScope.launch {
            observeScratchStatusUseCase.invoke().collect { status ->
                setState { copy(scratchState = status.status.name) }
            }
        }
    }

    data class UiState(
        val title: String = "Activate Screen",
        val activateButtonText: String = "Activate",
        val scratchState: String = "",
        val errorMessage: String? = null,
    ) : IState
}
