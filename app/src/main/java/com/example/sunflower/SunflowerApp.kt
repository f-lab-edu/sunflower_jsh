package com.example.sunflower

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalMaterial3Api
@Composable
fun SunflowerApp() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colorScheme.primaryContainer,
    )
    val navController = rememberNavController()
    AppNavHost(
        navController = navController,
    )
}

@ExperimentalMaterial3Api
@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "mainScreen",
    ) {
        val plantViewModel = PlantListViewModel()

        plantViewModel.init()

        composable(
            "mainScreen",
        ) {
            MainScreen(navController = navController, plantViewModel)
        }
        composable(
            "detailInfoScreen/{idx}/{fromGarden}",
            arguments = listOf(
                navArgument("idx") { type = NavType.IntType },
                navArgument("fromGarden") { type = NavType.BoolType },
            ),
        ) { backStackEntry ->
            backStackEntry.arguments?.let { it ->
                DetailInfoScreen(
                    it.getInt("idx"),
                    plantViewModel,
                    it.getBoolean("fromGarden"),
                )
            }
        }
    }
}
