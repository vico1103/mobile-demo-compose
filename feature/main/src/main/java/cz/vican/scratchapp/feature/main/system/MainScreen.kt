package cz.vican.scratchapp.feature.main.system

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import cz.vican.scratchapp.feature.main.presentation.MainViewModel
//import cz.vican.scratchapp.feature.main.presentation.MainViewModel.UiState
import org.koin.compose.getKoin

import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()
    val activity = LocalActivity.current

    BackHandler { activity?.finish() }
    Scaffold { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Text(text = state.title)
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = state.title)
    }



//    owner?.let {
//        val viewModel: MainViewModel = koinViewModel(viewModelStoreOwner = owner)
//    }

//    val viewModel: MainViewModel = koinViewModel()


//    MainScreenImpl(state)
}

//@Composable
//private fun MainScreenImpl(state: UiState) {
//    val activity = LocalActivity.current
//
//    BackHandler { activity?.finish() }
//}