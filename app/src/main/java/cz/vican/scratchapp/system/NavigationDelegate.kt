package cz.vican.scratchapp.system

import cz.vican.scratchapp.device.GlobalNavigationController

internal class NavigationDelegate(globalNavigationController: GlobalNavigationController) {
    val effect = globalNavigationController.effect

    companion object {
        const val MAIN = "main"
        const val SCRATCH = "scratch"
        const val ACTIVATE = "activate"
    }
}