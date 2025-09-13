package com.pomodoroapp.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Material Design 3 color definitions for the Pomodoro Timer app.
 * 
 * These colors support both light and dark themes, with dynamic color
 * support for Android 12+ devices.
 */

// Material 3 base colors - Dark theme
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

// Material 3 base colors - Light theme
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// Pomodoro-specific semantic colors
val WorkColor = Color(0xFFE53E3E)          // Red for work sessions
val BreakColor = Color(0xFF38A169)         // Green for break sessions
val LongBreakColor = Color(0xFF3182CE)     // Blue for long breaks

// Timer progress indicator colors
val TimerProgressWork = Color(0xFFFF6B6B)      // Vibrant red for work progress
val TimerProgressBreak = Color(0xFF4ECDC4)     // Teal for break progress
val TimerBackground = Color(0xFFE2E8F0)        // Light gray for progress background