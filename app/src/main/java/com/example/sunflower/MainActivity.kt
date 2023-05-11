package com.example.sunflower

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.sunflower.theme.SunflowerTheme

class MainActivity : AppCompatActivity() {

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val plantListViewModel: PlantListViewModel by viewModels()

        setContent {
            SunflowerTheme {
                SunflowerApp(this, plantListViewModel)
            }
        }
    }
}
