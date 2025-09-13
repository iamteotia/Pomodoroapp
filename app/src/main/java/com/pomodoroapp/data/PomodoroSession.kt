package com.pomodoroapp.data

/**
 * Represents the different types of Pomodoro sessions.
 * 
 * @param displayName The human-readable name for the session type
 * @param durationMinutes The duration of the session in minutes
 */
enum class PomodoroSession(val displayName: String, val durationMinutes: Int) {
    WORK("Work Time", 25),
    SHORT_BREAK("Short Break", 5),
    LONG_BREAK("Long Break", 15);

    /**
     * Returns the session duration in milliseconds.
     */
    fun getDurationMillis(): Long = durationMinutes * 60 * 1000L
}

/**
 * Represents the current state of the Pomodoro timer.
 * 
 * @param currentSession The current session type (work or break)
 * @param timeLeftMillis The remaining time in the current session (milliseconds)
 * @param isRunning Whether the timer is currently running
 * @param completedSessions The number of work sessions completed
 */
data class PomodoroState(
    val currentSession: PomodoroSession = PomodoroSession.WORK,
    val timeLeftMillis: Long = PomodoroSession.WORK.getDurationMillis(),
    val isRunning: Boolean = false,
    val completedSessions: Int = 0
)