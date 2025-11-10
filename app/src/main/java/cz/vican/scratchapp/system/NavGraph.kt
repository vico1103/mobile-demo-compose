package cz.vican.scratchapp.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.vican.scratchapp.device.GlobalNavigationController.NavigationEffect
import cz.vican.scratchapp.feature.main.system.MainScreen
import org.koin.compose.getKoin

@Composable
internal fun NavGraph(navHostController: NavHostController = rememberNavController()) {
    val delegate: NavigationDelegate = getKoin().get()

    NavHost(
        navController = navHostController,
        startDestination = NavigationDelegate.MAIN
    ) {
        composable(NavigationDelegate.MAIN) {
            MainScreen()
        }
    }

    LaunchedEffect(delegate.effect) {
        delegate.effect.collect { navigationEffect ->
            when (navigationEffect) {
                NavigationEffect.BackScreen -> navHostController.popBackStack()
                NavigationEffect.ToActivateScreen,
                    NavigationEffect.ToScratchScreen -> { /* No-op for now */}
            }
        }
    }
}