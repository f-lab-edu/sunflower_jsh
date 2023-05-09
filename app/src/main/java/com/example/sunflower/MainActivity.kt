package com.example.sunflower

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.SavedStateHandle
import com.example.sunflower.theme.SunflowerTheme

class MainActivity : AppCompatActivity() {

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val plantListViewModel = PlantListViewModel(SavedStateHandle())

        setContent {
            SunflowerTheme {
                SunflowerApp(this, plantListViewModel)
            }
        }
    }
}
