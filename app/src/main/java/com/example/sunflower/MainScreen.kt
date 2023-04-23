package com.example.sunflower

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview
@Composable
fun MainScreen() {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.Gray
    )
    ArtistCard()
}

@Composable
fun ArtistCard() {
    Column {
        Text("Alfred Sisley", color = MaterialTheme.colorScheme.primary)
        Text("3 minutes ago")
        Box {
        }
    }
}
