package com.example.sunflower

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sunflower.data.PlantViewData
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.jetbrains.annotations.Nullable

@ExperimentalMaterial3Api
@Composable
fun SunflowerApp(context: Context, plantListViewModel: PlantListViewModel) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colorScheme.primaryContainer,
    )
    val navController = rememberNavController()
    AppNavHost(
        context,
        plantListViewModel = plantListViewModel,
        navController = navController,
    )
}

@ExperimentalMaterial3Api
@Composable
fun AppNavHost(
    context: Context,
    plantListViewModel: PlantListViewModel,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "mainScreen",
    ) {
        val navigateToDetail: (String) -> Unit =
            { plantName -> navController.navigate("detailInfoScreen/$plantName") }
        val addPlantToGarden: (String, Context) -> Unit =
            { plantName, context -> plantListViewModel.addPlantToGarden(plantName, context) }
        val checkIsPlantInGarden: (PlantName, Context) -> IsPlanted = { plantName, context ->
            plantListViewModel.checkIsPlantPlanted(plantName, context)
        }

        @Nullable
        val findSelectedPlant: (PlantName) -> PlantViewData? = { plantName ->
            plantListViewModel.findSelectedPlantViewData(plantName)
        }

        composable(
            "mainScreen",
        ) {
            MainScreen(onClickPlantCard = navigateToDetail, plantListViewModel = plantListViewModel)
        }
        composable(
            "detailInfoScreen/{plantName}",
            arguments = listOf(
                navArgument("plantName") { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            backStackEntry.arguments?.let { bundle ->
                findSelectedPlant(bundle.getString("plantName").toString())?.let { plantViewData ->
                    DetailInfoScreen(
                        onClickAddButton = { addPlantToGarden(plantViewData.plantName, context) },
                        plantViewData = plantViewData,
                        checkIsPlantPlanted = { checkIsPlantInGarden(plantViewData.plantName, context) },
                    )
                }
            }
        }
    }
}
