package com.pomodoroapp.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pomodoroapp.data.PomodoroSession
import com.pomodoroapp.ui.theme.*
import com.pomodoroapp.utils.formatTime

/**
 * A circular progress indicator that shows the remaining time in the current Pomodoro session.
 * 
 * Features:
 * - Animated progress arc with smooth transitions
 * - Color-coded progress based on session type (work/break)
 * - Central time display with session information
 * - Material Design 3 styling
 * 
 * @param timeLeft The remaining time in milliseconds
 * @param totalTime The total duration of the current session in milliseconds
 * @param currentSession The current session type (work or break)
 * @param isRunning Whether the timer is currently running
 * @param modifier Modifier for customizing the component's layout
 */
@Composable
fun CircularTimerProgress(
    timeLeft: Long,
    totalTime: Long,
    currentSession: PomodoroSession,
    isRunning: Boolean,
    modifier: Modifier = Modifier
) {
    // Animate progress changes smoothly
    val progress by animateFloatAsState(
        targetValue = if (totalTime > 0) timeLeft.toFloat() / totalTime.toFloat() else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        label = "timer_progress"
    )

    // Choose progress color based on session type
    val progressColor = when (currentSession) {
        PomodoroSession.WORK -> TimerProgressWork
        PomodoroSession.SHORT_BREAK, PomodoroSession.LONG_BREAK -> TimerProgressBreak
    }

    Box(
        modifier = modifier.size(280.dp),
        contentAlignment = Alignment.Center
    ) {
        // Canvas for drawing the circular progress
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 16.dp.toPx()
            val radius = (size.minDimension - strokeWidth) / 2

            // Background circle (inactive state)
            drawCircle(
                color = TimerBackground.copy(alpha = 0.3f),
                radius = radius,
                style = Stroke(strokeWidth)
            )

            // Progress arc (active portion)
            drawArc(
                color = progressColor,
                startAngle = -90f, // Start from top
                sweepAngle = progress * 360f,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round
                )
            )
        }

        // Central content - time display and session info
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Main time display
            Text(
                text = formatTime(timeLeft),
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Session type indicator
            Text(
                text = currentSession.displayName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}