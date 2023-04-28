package com.example.sunflower

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyGardenScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.primaryContainer,
            )
    ) {
        GardenRecyclerView()
    }
}

@Composable
fun GardenRecyclerView(){
    LazyColumn() {
        items(
            count = PlantDataObject.plantList.size,
            itemContent = { PlantItem(PlantDataObject.plantList[it])}
        )
    }
}
