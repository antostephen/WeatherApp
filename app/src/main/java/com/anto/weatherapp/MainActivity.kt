package com.anto.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.compose.rememberNavController
import com.anto.weatherapp.navigation.BottomNavigationBar
import com.anto.weatherapp.presentation.compossables.screens.NavGraphs
import com.anto.weatherapp.ui.theme.WeatherTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                val navController = rememberNavController()
                val navHostEngine = rememberNavHostEngine()

                BottomNavigationBar(navController = navController) {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .testTag("bottom_navigation_bar")
                    ) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            navController = navController,
                            engine = navHostEngine
                        )

                    }
                }
            }
        }
    }
}


