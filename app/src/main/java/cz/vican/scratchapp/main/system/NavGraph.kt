package cz.vican.scratchapp.main.system

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cz.vican.scratchapp.feature.activate.system.ActivateScreen
import cz.vican.scratchapp.scratch.system.ScratchScreen

@Composable
internal fun NavGraph(
    navHostController: NavHostController
){
    NavHost(navController = navHostController, startDestination = "main") {
        composable(route = "main") {
//            MainScreen(navController = navHostController)
        }
        composable(route = "activate") {
            ActivateScreen()
        }
        composable(route = "scratch") {
            ScratchScreen()
        }
    }
}