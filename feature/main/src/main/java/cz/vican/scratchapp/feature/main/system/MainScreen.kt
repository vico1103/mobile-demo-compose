package cz.vican.scratchapp.feature.main.system

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.vican.scratchapp.feature.main.presentation.MainViewModel
import cz.vican.scratchapp.feature.main.presentation.MainViewModel.UiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    MainScreenImpl(
        state,
        viewModel::onScratchButtonClicked,
        viewModel::onActivateButtonClicked
    )

}

@Composable
private fun MainScreenImpl(
    state: UiState,
    onScratchCardClicked: () -> Unit,
    onActivateCardClicked: () -> Unit
) {
    val activity = LocalActivity.current
    BackHandler { activity?.finish() }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = state.scratchState,
                textAlign = TextAlign.Center
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                onClick = onScratchCardClicked
            ) {
                Text(text = state.scratchButtonText)
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                onClick = onActivateCardClicked
            ) {
                Text(text = state.activateButtonText)
            }
        }
    }
}