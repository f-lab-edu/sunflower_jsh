package com.example.sunflower

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview
@Composable
fun MainScreen() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colorScheme.primary
    )
    ThemeTest()
}

@Composable
fun ThemeTest() {
    Column {
        Text("Theme Test", color = MaterialTheme.colorScheme.primary)
    }
}
