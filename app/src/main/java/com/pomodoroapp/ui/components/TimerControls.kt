package com.pomodoroapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Timer control buttons for start, pause, and reset functionality.
 * 
 * Features:
 * - Material Design 3 button styles
 * - Proper accessibility support
 * - Responsive button states
 * - Icon + text button layout
 * 
 * @param isRunning Whether the timer is currently running
 * @param onStartClick Callback for start/resume button
 * @param onPauseClick Callback for pause button
 * @param onResetClick Callback for reset button
 * @param modifier Modifier for customizing the component's layout
 */
@Composable
fun TimerControls(
    isRunning: Boolean,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Start/Resume Button - Primary action
        FilledTonalButton(
            onClick = onStartClick,
            enabled = !isRunning,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = if (isRunning) "Running" else "Start",
                style = MaterialTheme.typography.labelLarge
            )
        }

        // Pause Button - Secondary action
        OutlinedButton(
            onClick = onPauseClick,
            enabled = isRunning,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.Pause,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Pause",
                style = MaterialTheme.typography.labelLarge
            )
        }

        // Reset Button - Tertiary action
        OutlinedButton(
            onClick = onResetClick,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Reset",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}