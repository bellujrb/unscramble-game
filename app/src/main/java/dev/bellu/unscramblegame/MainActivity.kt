package dev.bellu.unscramblegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.bellu.unscramblegame.presentation.screens.HomeScreen
import dev.bellu.unscramblegame.presentation.screens.WelcomeScreen
import dev.bellu.unscramblegame.presentation.theme.UnscrambleGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "welcome"){
                composable("welcome"){ WelcomeScreen(navController) }
                composable("home"){ HomeScreen() }
            }
        }
    }
}