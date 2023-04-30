package com.example.sunflower

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sunflower.data.MockUpDataList
import com.example.sunflower.data.PlantViewData
import kotlinx.coroutines.flow.StateFlow

@ExperimentalMaterial3Api
@Composable
fun PlantListScreen(
    navController: NavController,
    isPlantedState: StateFlow<List<PlantViewData>>
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.secondary,
            ),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 8.dp,
        ),
    ) {
        items(
            count = MockUpDataList.plantList.size,
            itemContent = {
                PlantCardView(it, { navController.navigate("detailInfoScreen/${it}") }, isPlantedState)
            },
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun PlantCardView(idx: Int, onNavigateToDetail: () -> Unit, plantListState: StateFlow<List<PlantViewData>>) {
    Card(
        onClick = onNavigateToDetail,
        shape = card,
        modifier = Modifier.padding(
            vertical = 8.dp,
            horizontal = 8.dp,
        ),
    ) {
        Column(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.primaryContainer,
            ),
        ) {
            PlantImage(
                plantListState.collectAsState().value[idx].image
            )

            Text(
                text = plantListState.collectAsState().value[idx].name,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(
                        vertical = 12.dp,
                    ),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
