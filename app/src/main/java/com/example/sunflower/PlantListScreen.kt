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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sunflower.data.PlantViewData

@ExperimentalMaterial3Api
@Composable
fun PlantListScreen(
    plantViewDataList: List<PlantViewData>,
    onClickPlantCard: (Int, Boolean) -> Unit,
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
        val isFromGardenScreen = false
        items(
            count = plantViewDataList.size,
            itemContent = { index ->
                PlantCardView(onCardClick = {
                    onClickPlantCard(index, isFromGardenScreen)
                }, plantViewData = plantViewDataList[index])
            },
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun PlantCardView(plantViewData: PlantViewData, onCardClick: () -> Unit) {
    Card(
        shape = PreDefinedCornerBorders.customCard,
        onClick = onCardClick,
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
            AsyncImage(
                model = plantViewData.imageUrl,
                contentDescription = null,
            )
            Text(
                text = plantViewData.name,
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
