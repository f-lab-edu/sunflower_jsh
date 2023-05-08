package com.example.sunflower.garden

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sunflower.R
import com.example.sunflower.data.PlantViewData

@ExperimentalMaterial3Api
@Composable
fun PlantCard(plantViewData: PlantViewData, onCardClick: () -> Unit) {
    if (plantViewData.isPlanted) {
        Card(
            onClick = onCardClick,
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 8.dp,
            ),
        ) {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer),
            ) {
                PlantImage(plantViewData.imageResId)
                Text(
                    text = plantViewData.name,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(vertical = 12.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = stringResource(R.string.planted),
                    modifier = Modifier
                        .align(CenterHorizontally),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Text(
                    text = plantViewData.plantedDate,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(bottom = 12.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = stringResource(R.string.lastWatered),
                    modifier = Modifier
                        .align(CenterHorizontally),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Text(
                    text = plantViewData.lastWateredDate,
                    modifier = Modifier
                        .align(CenterHorizontally),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = "water in ${plantViewData.wateringCycle} days",
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(bottom = 12.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Composable
fun PlantImage(
    @DrawableRes imageResId: Int,
) {
    Image(
        painter = painterResource(
            id = imageResId,
        ),
        contentDescription = null,
    )
}
