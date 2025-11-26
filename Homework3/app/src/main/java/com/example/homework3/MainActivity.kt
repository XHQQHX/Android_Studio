package com.example.homework3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.homework3.screen.LivesScreen
import com.example.homework3.screen.ForecastsScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "lives"
            ) {
                composable("lives") {
                    LivesScreen(
                        onNavigateToForecasts = {navController.navigate("forecasts")}
                    )
                }
                composable("forecasts") {
                    ForecastsScreen(
                        onNavigateToLives = {navController.navigate("lives")}
                    )
                }
            }

        }
    }
}