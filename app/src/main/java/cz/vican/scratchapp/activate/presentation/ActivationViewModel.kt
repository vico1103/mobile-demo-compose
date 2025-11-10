package cz.vican.scratchapp.activate.presentation

import cz.vican.architecture.presentation.AbstractViewModel
import cz.vican.architecture.presentation.AbstractViewModel.IState
import cz.vican.scratchapp.activate.presentation.ActivationViewModel.State

class ActivationViewModel(

): AbstractViewModel<State>() {
    override fun createInitialState(): State {
        TODO("Not yet implemented")
    }

    data class State(
        val title: String,
        val activate: String,
        val code: String,

    ): IState
}