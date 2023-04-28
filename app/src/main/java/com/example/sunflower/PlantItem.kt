package com.example.sunflower

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PlantCard(plantData: PlantInfo) {
    Card(
        shape = card,
        modifier = Modifier.padding(
            vertical = 8.dp,
            horizontal = 8.dp,
        )
    ) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)) {
            PlantImage(plantData.image)
            Text(
                text = plantData.name,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(vertical = 12.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Planted", modifier = Modifier
                    .align(CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = plantData.planted, modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom = 12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Last Watered", modifier = Modifier
                    .align(CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = plantData.lastWatered, modifier = Modifier
                    .align(CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "water in ${plantData.wateringCycle} days", modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom = 12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun PlantImage(@DrawableRes image: Int) {
    Image(painter = painterResource(id = image), contentDescription = null)
}
