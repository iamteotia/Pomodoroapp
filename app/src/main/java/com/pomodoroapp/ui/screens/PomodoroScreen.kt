package com.pomodoroapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pomodoroapp.ui.components.CircularTimerProgress
import com.pomodoroapp.ui.components.TimerControls
import com.pomodoroapp.viewmodel.PomodoroViewModel

/**
 * Main screen for the Pomodoro Timer application.
 * 
 * This screen displays:
 * - Session completion counter
 * - Circular timer progress indicator
 * - Timer control buttons
 * - Current session information
 * 
 * Features:
 * - Material Design 3 layout and components
 * - Responsive design that works on different screen sizes
 * - Clean MVVM architecture integration
 * 
 * @param modifier Modifier for customizing the screen's layout
 * @param viewModel The ViewModel that manages timer state and logic
 */
@Composable
fun PomodoroScreen(
    modifier: Modifier = Modifier,
    viewModel: PomodoroViewModel = viewModel()
) {
    // Observe timer state from ViewModel
    val pomodoroState by viewModel.pomodoroState.collectAsState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Session completion counter card
            Card(
                modifier = Modifier.padding(bottom = 32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                shape = MaterialTheme.shapes.large,
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sessions Completed",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "${pomodoroState.completedSessions}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Main circular timer progress indicator
            CircularTimerProgress(
                timeLeft = pomodoroState.timeLeftMillis,
                totalTime = pomodoroState.currentSession.getDurationMillis(),
                currentSession = pomodoroState.currentSession,
                isRunning = pomodoroState.isRunning
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Timer control buttons
            TimerControls(
                isRunning = pomodoroState.isRunning,
                onStartClick = { 
                    viewModel.startTimer() 
                },
                onPauseClick = { 
                    viewModel.pauseTimer() 
                },
                onResetClick = { 
                    viewModel.resetTimer() 
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Additional session info
            Text(
                text = when {
                    pomodoroState.isRunning -> "Focus time! Keep going 💪"
                    pomodoroState.completedSessions == 0 -> "Ready to start your first session?"
                    pomodoroState.completedSessions % 4 == 0 && pomodoroState.completedSessions > 0 -> 
                        "Great job! Time for a long break 🎉"
                    else -> "Take a short break and recharge ☕"
                },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}