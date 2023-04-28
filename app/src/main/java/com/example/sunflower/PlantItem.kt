package com.example.sunflower

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PlantItem(plantData: PlantData){
    Column {
        PlantImage()
        Text(text = plantData.name)
    }
}

@Composable
fun PlantImage(){

}
