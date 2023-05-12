package com.example.sunflower

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sunflower.data.PlantViewData
import com.example.sunflower.garden.PlantImage

@ExperimentalMaterial3Api
@Composable
fun PlantListScreen(
    plantViewDataList: List<PlantViewData>,
    onClickPlantCard: (plantName: String) -> Unit,
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
        content = { plantListScreenContent(plantViewDataList, onClickPlantCard) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
fun LazyGridScope.plantListScreenContent(
    plantViewDataList: List<PlantViewData>,
    onClickPlantCard: (plantName: String) -> Unit,
) {
    items(plantViewDataList, itemContent = { item ->
        PlantCardView(item) { onClickPlantCard(item.plantName) }
    })
}

@ExperimentalMaterial3Api
@Composable
fun PlantCardView(plantViewData: PlantViewData, onClickCard: () -> Unit) {
    Card(
        shape = PreDefinedCornerBorders.customCard,
        onClick = onClickCard,
        modifier = Modifier.padding(
            vertical = 8.dp,
            horizontal = 8.dp,
        ),
        content = { PlantCardViewContent(plantViewData) },
    )
}

@Composable
fun PlantCardViewContent(plantViewData: PlantViewData) {
    Column(
        modifier = Modifier.background(
            MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        PlantImage(
            plantViewData.imageResId,
        )

        Text(
            text = plantViewData.plantName,
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
