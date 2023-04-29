package com.example.sunflower

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyGardenScreen() {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 8.dp,
        ),
            .background(
                MaterialTheme.colorScheme.primaryContainer,
            )
    ) {
        items(
            count = PlantDataObject.plantList.size,
            itemContent = { PlantCard(PlantDataObject.plantList[it]) },
        )
    }
}
