package com.example.sunflower

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun PlantListScreen() {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 8.dp,
        )
    ) {
        items(
            count = PlantDataObject.plantList.size,
            itemContent = { PlantCard(PlantDataObject.plantList[it]) }
        )
    }
}
