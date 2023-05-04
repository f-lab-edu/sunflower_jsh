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

        plantListViewModel.init()

        val navigateToDetail: (Int) -> (Boolean) -> Unit =
            { i -> { b -> navController.navigate("detailInfoScreen/$i/$b") } }
        val addPlantToGarden: (Int) -> (Context) -> Unit =
            { i -> { context -> plantListViewModel.addPlantToGarden(i, context) } }
        val checkAdded: (Int) -> Boolean = { i ->
            plantListViewModel.checkAdded(i)
        }

        composable(
            "mainScreen",
        ) {
            MainScreen(navigateToDetail, plantListViewModel)
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
                    addPlantToGarden(it.getInt("idx")),
                    if (it.getBoolean("fromGarden")) {
                        plantListViewModel.gardenListState.collectAsState().value[it.getInt("idx")]
                    } else {
                        plantListViewModel.plantListState.collectAsState().value[it.getInt("idx")]
                    },
                ) { checkAdded(it.getInt("idx")) }
            }
        }
    }
}
