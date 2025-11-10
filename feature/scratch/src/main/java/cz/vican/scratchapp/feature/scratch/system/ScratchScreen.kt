package cz.vican.scratchapp.feature.scratch.system

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.unit.dp
import cz.vican.scratchapp.feature.scratch.presentation.ScratchViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ScratchScreen() {
    val viewModel: ScratchViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    ScratchScreenImpl(
        state,
        viewModel::onBackButtonClicked,
        viewModel::onScratchCard
    )

}

@Composable
private fun ScratchScreenImpl(
    state: ScratchViewModel.UiState,
    onBackClicked: () -> Unit,
    onScratchCardClicked: () -> Unit,
) {

    BackHandler(onBack = onBackClicked)
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
        ) {
            Text(text = state.scratchState)
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                onClick = onScratchCardClicked,
            ) {
                Text(text = state.scratchButtonText)
            }
        }
    }
}