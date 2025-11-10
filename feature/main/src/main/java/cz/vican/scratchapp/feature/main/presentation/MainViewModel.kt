package cz.vican.scratchapp.feature.main.presentation

import cz.vican.scratchapp.feature.main.domain.NavigationController
import cz.vican.scratchapp.feature.main.presentation.MainViewModel.UiState
import cz.vican.scratchapp.library.architecture.presentation.AbstractViewModel

internal class MainViewModel(
    private val navigationController: NavigationController
): AbstractViewModel<UiState>() {

    override fun createInitialState(): UiState {
       return UiState(
            title = "Main Screen",
            scratchButtonText = "Scratch Card",
            activateButtonText = "Activate Card"
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
        val scratchButtonText: String,
        val activateButtonText: String
    ) : IState
}