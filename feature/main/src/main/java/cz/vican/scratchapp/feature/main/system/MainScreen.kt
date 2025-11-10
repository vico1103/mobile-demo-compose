package cz.vican.scratchapp.feature.main.system

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cz.vican.scratchapp.feature.main.presentation.MainViewModel
import cz.vican.scratchapp.feature.main.presentation.MainViewModel.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val viewModel = koinViewModel<MainViewModel>()
    val state by viewModel.uiState.collectAsState()

    MainScreenImpl(state)
}

@Composable
private fun MainScreenImpl(state: UiState) {
    val activity = LocalActivity.current

    BackHandler { activity?.finish() }
}