package cz.vican.scratchapp.device

import cz.vican.scratchapp.feature.main.domain.MainNavigationController
import cz.vican.scratchapp.feature.scratch.domain.ScratchNavigationController
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class GlobalNavigationController :
    MainNavigationController,
    ScratchNavigationController {

    private val mutableEffect = MutableSharedFlow<NavigationEffect>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val effect = mutableEffect.asSharedFlow()

    override fun navigateToActivateScreen() {
        mutableEffect.tryEmit(NavigationEffect.ToActivateScreen)
    }

    override fun navigateToScratchScreen() {
        mutableEffect.tryEmit(NavigationEffect.ToScratchScreen)
    }

    override fun navigateBack() {
        mutableEffect.tryEmit(NavigationEffect.BackScreen)
    }

    sealed interface NavigationEffect {
        object ToActivateScreen : NavigationEffect
        object ToScratchScreen : NavigationEffect
        object BackScreen : NavigationEffect
    }
}