package cz.vican.scratchapp.feature.main.presentation

import androidx.lifecycle.viewModelScope
import cz.vican.scratchapp.feature.main.domain.MainNavigationController
import cz.vican.scratchapp.feature.main.presentation.MainViewModel.UiState
import cz.vican.scratchapp.library.architecture.presentation.AbstractViewModel
import cz.vican.scratchcard.generic.scratchcard.domain.ObserveScratchStatusUseCase
import kotlinx.coroutines.launch


internal class MainViewModel(
    private val navigationController: MainNavigationController,
    private val observeScratchStatusUseCase: ObserveScratchStatusUseCase
) : AbstractViewModel<UiState>() {

    init {
        viewModelScope.launch {
            observeScratchStatusUseCase.invoke().collect {
                setState { copy(scratchState = it.status.name) }
            }
        }
    }

    override fun createInitialState(): UiState {
        return UiState(
            title = "Main Screen",
            scratchButtonText = "Scratch Card",
            activateButtonText = "Activate Card",
            scratchState = ""
        )
    }

    fun onScratchButtonClicked() {
        navigationController.navigateToScratchScreen()
    }

    fun onActivateButtonClicked() {
        navigationController.navigateToActivateScreen()
    }


    data class UiState(
        val title: String,
        val scratchState: String,
        val scratchButtonText: String,
        val activateButtonText: String
    ) : IState
}