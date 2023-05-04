package com.example.sunflower.garden

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sunflower.data.PlantViewData
import kotlinx.coroutines.flow.StateFlow

@ExperimentalMaterial3Api
@Composable
fun MyGardenScreen(onPlantClick: (Int) -> (Boolean) -> Unit, gardenListState: StateFlow<List<PlantViewData>>) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 8.dp,
        ),
    ) {
        items(
            count = gardenListState.value.size,
            itemContent = {
                if (gardenListState.value.isNotEmpty()) {
                    PlantCard(
                        { onPlantClick(it)(true) },
                        gardenListState.collectAsState().value[it],
                    )
                }
            },
        )
    }
}
