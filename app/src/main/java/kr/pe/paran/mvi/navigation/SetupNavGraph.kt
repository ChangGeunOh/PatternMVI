package kr.pe.paran.mvi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kr.pe.paran.mvi.presentation.main.MainScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Route.MainScreen,
        builder = {
            composable(Route.MainScreen) {
                MainScreen(navController = navHostController)
            }
        }
    )

}