package cz.vican.architecture.presentation

import androidx.lifecycle.ViewModel
import cz.vican.architecture.presentation.AbstractViewModel.IState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class AbstractViewModel<State : IState>: ViewModel() {
    interface IState

    private val mutableState: MutableStateFlow<State> = MutableStateFlow(createInitialState())

    val uiState: StateFlow<State> = mutableState.asStateFlow()

    protected val currentState: State
        get() = mutableState.value

    protected fun setState(reducer: State.() -> State) {
        mutableState.value = reducer(mutableState.value)
    }

    protected abstract fun createInitialState(): State
}