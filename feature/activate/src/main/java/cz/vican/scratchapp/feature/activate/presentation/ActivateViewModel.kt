package cz.vican.scratchapp.feature.activate.presentation

import androidx.lifecycle.viewModelScope
import cz.vican.architecture.presentation.AbstractViewModel
import cz.vican.scratchapp.feature.activate.domain.ActivateUseCase
import cz.vican.scratchcard.domain.LoadScratchStatusUseCase
import cz.vican.scratchcard.domain.ObserveScratchStatusUseCase
import cz.vican.scratchcard.domain.SetScratchStatusUseCase
import cz.vican.scratchcard.model.ScratchCardStatus
import cz.vican.scratchcard.model.ScratchCardStatus.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class ActivateViewModel(
    private val activateUseCase: ActivateUseCase,
    private val observeScratchStatusUseCase: ObserveScratchStatusUseCase,
    private val loadScratchStatusUseCase: LoadScratchStatusUseCase,
    private val setScratchStatusUseCase: SetScratchStatusUseCase
) : AbstractViewModel<ActivateViewModel.State>() {
    override fun createInitialState(): State {
        return State(title = "Activate Feature")
    }

    init {
        observeScratchStatus()
    }

    fun activateCard() {
        val scratchCard = loadScratchStatusUseCase.invoke()
        when (scratchCard.status) {
            Status.UNSCRATCHED, Status.REDEEMED -> return
            Status.REVEALED -> activate(scratchCard)
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

            }
        }
    }

    data class State(
        val title: String,
        val isScratched: Boolean = false,
        val isActivated: Boolean = false,
        val errorMessage: String? = null,
    ) : IState
}
