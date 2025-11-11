package cz.vican.scratchapp.feature.scratch.system

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .semantics(
                        properties = {
                            contentDescription = "${state.stateTitle}: ${state.scratchState}"
                        }
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = state.stateTitle,
                    fontSize = 14.sp,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = state.scratchState,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                onClick = onScratchCardClicked
            ) {
                Text(text = state.scratchButtonText)
            }
        }
    }
}