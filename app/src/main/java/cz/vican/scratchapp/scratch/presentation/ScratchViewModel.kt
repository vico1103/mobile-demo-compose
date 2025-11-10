package cz.vican.scratchapp.scratch.presentation

import androidx.lifecycle.viewModelScope
import cz.vican.architecture.presentation.AbstractViewModel
import cz.vican.scratchapp.scratch.domain.ScratchUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi

internal class ScratchViewModel(
    private val scratchUseCase: ScratchUseCase,
    private val setScratchUseCase: SetScratchUseCase
) : AbstractViewModel<ScratchViewModel.State>() {
    private var scratchJob: Job? = null


    override fun createInitialState(): State {
        return State()
    }

    @OptIn(ExperimentalUuidApi::class)
    fun scratchCard() {
        scratchJob = viewModelScope.launch {
            scratchUseCase.invoke().collect { code ->
                setScratchUseCase
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        cancelScratch()
    }

    private fun cancelScratch() {
        scratchJob?.cancel()
        scratchJob = null
    }

    data class State(
        val title: String = "Scratch card",
        val activate: String = "Activate",
        val code: String = "",
        val isScratched: Boolean = false
    ) : IState
}