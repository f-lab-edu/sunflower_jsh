package com.example.sunflower

import android.content.Context
import android.widget.Toast
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
import com.example.sunflower.result.ResultOfFindingPlantIndex
import com.example.sunflower.result.ResultOfFunction
import com.google.accompanist.systemuicontroller.rememberSystemUiController

internal const val MAIN_SCREEN = "mainScreen"
internal const val DETAIL_INFO_SCREEN = "detailInfoScreen"
internal const val PLANT_NAME = "plantName"

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
        startDestination = MAIN_SCREEN,
    ) {
        val navigateToDetail: (plantName: String) -> Unit =
            { plantName -> navController.navigate("$DETAIL_INFO_SCREEN/$plantName") }

        fun addPlantToGarden(plantName: String) {
            when (plantListViewModel.addPlantToGarden(plantName)) {
                ResultOfFunction.Success -> Toast.makeText(
                    context,
                    context.getText(R.string.add_toast_message),
                    Toast.LENGTH_SHORT,
                ).show()
                ResultOfFunction.Failure -> Toast.makeText(
                    context,
                    context.getText(R.string.error_message),
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }

        composable(
            MAIN_SCREEN,
        ) {
            MainScreen(plantListViewModel, navigateToDetail)
        }
        composable(
            "$DETAIL_INFO_SCREEN/{$PLANT_NAME}",
            arguments = listOf(
                navArgument(PLANT_NAME) { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val bundle = backStackEntry.arguments ?: return@composable
            val resultOfFindingPlantIndex =
                plantListViewModel.findSelectedPlantIndex(bundle.getString(PLANT_NAME).toString())
            val plantIndex = when (resultOfFindingPlantIndex) {
                is ResultOfFindingPlantIndex.Success -> resultOfFindingPlantIndex.index
                is ResultOfFindingPlantIndex.Failure -> return@composable
            }
            DetailInfoScreen(plantListViewModel.plantListState.collectAsState().value[plantIndex]) {
                addPlantToGarden(bundle.getString(PLANT_NAME).toString())
            }
        }
    }
}
