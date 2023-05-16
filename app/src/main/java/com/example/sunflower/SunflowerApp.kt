package com.example.sunflower

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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

private const val MAIN_SCREEN = "mainScreen"
private const val DETAIL_INFO_SCREEN = "detailInfoScreen"
private const val PLANT_NAME = "plantName"

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
private fun AppNavHost(
    plantListViewModel: PlantListViewModel,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MAIN_SCREEN,
    ) {
        composable(
            MAIN_SCREEN,
        ) {
            MainScreen(plantListViewModel, navController::goToDetail)
        }
        composable(
            "$DETAIL_INFO_SCREEN/{$PLANT_NAME}",
            arguments = listOf(
                navArgument(PLANT_NAME) { type = NavType.StringType },
            ),
            content = { backStackEntry ->
                val plantName = backStackEntry.arguments?.getString(PLANT_NAME).orEmpty()
                DetailInfoScreenInner(plantListViewModel, plantName)
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailInfoScreenInner(viewModel: PlantListViewModel, plantName: String) {
    val foundPlantState =
        viewModel.findPlantByNameAsFlow(plantName).collectAsState(initial = null)
    val foundPlant = foundPlantState.value
    if (foundPlant != null) {
        val context = LocalContext.current
        DetailInfoScreen(foundPlant) {
            attemptToPlant(context, viewModel, foundPlant)
        }
    }
}

private fun NavController.goToDetail(plantName: String) {
    navigate("$DETAIL_INFO_SCREEN/$plantName")
}

private fun attemptToPlant(
    context: Context,
    plantListViewModel: PlantListViewModel,
    plant: PlantViewData,
) {
    when (plantListViewModel.plantToGarden(plant)) {
        PlantListViewModel.PlantToGardenResult.Success -> Toast.makeText(
            context,
            context.getText(R.string.add_toast_message),
            Toast.LENGTH_SHORT,
        ).show()

        PlantListViewModel.PlantToGardenResult.PlantNotFound -> Toast.makeText(
            context,
            context.getText(R.string.error_message),
            Toast.LENGTH_SHORT,
        ).show()
    }
}
