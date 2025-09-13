package com.pomodoroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.pomodoroapp.ui.screens.PomodoroScreen
import com.pomodoroapp.ui.theme.PomodoroTimerTheme

/**
 * Main activity for the Pomodoro Timer app.
 * 
 * This activity sets up the Jetpack Compose UI with Material Design 3 theming
 * and displays the main Pomodoro timer screen.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PomodoroTimerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PomodoroScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}