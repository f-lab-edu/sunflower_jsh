package com.example.sunflower

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
        val plantListViewModel = PlantListViewModel()

        val navigateToDetail: (Int, Boolean) -> Unit =
            { index, isFromGardenScreen -> navController.navigate("detailInfoScreen/$index/$isFromGardenScreen") }
        val addPlantToGarden: (Int) -> (Context) -> Unit =
            { index -> { context -> plantListViewModel.addPlantToGarden(index, context) } }
        val checkIsPlantInGardenAtIndex: (Int) -> Boolean = { i ->
            plantListViewModel.checkIsPlantPlantedAtIndex(i)
        }

        composable(
            "mainScreen",
        ) {
            MainScreen(navigateToDetail, plantListViewModel)
        }
        composable(
            "detailInfoScreen/{index}/{isFromGarden}",
            arguments = listOf(
                navArgument("index") { type = NavType.IntType },
                navArgument("isFromGarden") { type = NavType.BoolType },
            ),
        ) { backStackEntry ->
            backStackEntry.arguments?.let { it ->
                DetailInfoScreen(
                    addPlantToGarden(it.getInt("index")),
                    if (it.getBoolean("isFromGarden")) {
                        plantListViewModel.gardenListState.collectAsState().value[it.getInt("index")]
                    } else {
                        plantListViewModel.plantListState.collectAsState().value[it.getInt("index")]
                    },
                ) { checkIsPlantInGardenAtIndex(it.getInt("index")) }
            }
        }
    }
}
