package com.pomodoroapp.utils

/**
 * Utility functions for formatting time values in the Pomodoro Timer app.
 */

/**
 * Formats a time value in milliseconds to MM:SS format.
 * 
 * @param millis The time value in milliseconds
 * @return A formatted string in MM:SS format (e.g., "25:00", "04:32")
 */
fun formatTime(millis: Long): String {
    val totalSeconds = millis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}