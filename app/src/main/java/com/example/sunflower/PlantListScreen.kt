package com.example.sunflower

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlantListScreen() {
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
                Card(
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
                            MockUpDataList.plantList[it].image,
                        )

                        Text(
                            text = MockUpDataList.plantList[it].name,
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
            },
        )
    }
}
