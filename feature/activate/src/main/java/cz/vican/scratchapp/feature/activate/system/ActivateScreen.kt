package cz.vican.scratchapp.feature.activate.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cz.vican.scratchapp.feature.activate.presentation.ActivateViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ActivateScreen() {
    val viewModel: ActivateViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    ActivationScreenImpl(state = state, viewModel::activateCard)
}

@Composable
private fun ActivationScreenImpl(state: ActivateViewModel.State, activate: () -> Unit) {
    // Implement the UI based on the state
}