package com.example.sunflower

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sunflower.data.PlantViewData
import com.google.accompanist.systemuicontroller.rememberSystemUiController

internal const val MAIN_SCREEN = "mainScreen"
internal const val DETAIL_INFO_SCREEN = "detailInfoScreen"
internal const val PLANT_NAME = "plantName"

@ExperimentalMaterial3Api
@Composable
fun SunflowerApp(plantListViewModel: PlantListViewModel) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colorScheme.primaryContainer,
    )
    val navController = rememberNavController()
    AppNavHost(
        plantListViewModel = plantListViewModel,
        navController = navController,
    )
}

@ExperimentalMaterial3Api
@Composable
fun AppNavHost(
    // compose에서는 Context를 아래로 흘려보내시면 안됩니다. "LocalContext.current"를 사용하세요.
    plantListViewModel: PlantListViewModel,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MAIN_SCREEN,
    ) {
        composable(MAIN_SCREEN) {
            // To. 수현: 이런 형태로도 method reference를 사용할 수 있습니다.
            MainScreen(plantListViewModel, navController::goToDetail)
        }
        composable(
            "$DETAIL_INFO_SCREEN/{$PLANT_NAME}",
            arguments = listOf(
                navArgument(PLANT_NAME, builder = { type = NavType.StringType }),
            ),
            content = { backStackEntry ->
                val plantName = backStackEntry.arguments?.getString(PLANT_NAME).orEmpty()
                val foundPlant = plantListViewModel.findPlantByName(plantName)
                    ?: return@composable
                val context = LocalContext.current
                DetailInfoScreen(foundPlant) {
                    attemptToPlant(context, plantListViewModel, foundPlant)
                }
            },
        )
    }
}

private fun NavController.goToDetail(plantName: String) {
    navigate("$DETAIL_INFO_SCREEN/$plantName")
}

private fun attemptToPlant(
    context: Context,
    plantListViewModel: PlantListViewModel,
    plant: PlantViewData
) {
    when (plantListViewModel.plantToGarden(plant)) {
        PlantListViewModel.PlantToGardenResult.Success ->
            Toast.makeText(context, R.string.add_toast_message, Toast.LENGTH_SHORT).show()

        PlantListViewModel.PlantToGardenResult.PlantNotFound ->
            Toast.makeText(context, R.string.error_message, Toast.LENGTH_SHORT).show()
    }
}
