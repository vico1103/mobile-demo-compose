package cz.vican.scratchapp.feature.activate.system

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.vican.scratchapp.feature.activate.presentation.ActivateViewModel
import cz.vican.scratchapp.feature.activate.presentation.ActivateViewModel.UiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ActivateScreen() {
    val viewModel: ActivateViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    ActivateScreenImpl(
        state,
        viewModel::onBackClicked,
        viewModel::onActivateCard
    )

}

@Composable
private fun ActivateScreenImpl(
    state: UiState,
    onBackClicked: () -> Unit,
    onActivateCardClicked: () -> Unit,
) {
    BackHandler(onBack = onBackClicked)
    Scaffold(
        snackbarHost = { state.errorMessage?.let { Snackbar { Text(it) } } }
    ) { innerPadding ->
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
                onClick = onActivateCardClicked
            ) {
                Text(text = state.activateButtonText)
            }
        }
    }
}