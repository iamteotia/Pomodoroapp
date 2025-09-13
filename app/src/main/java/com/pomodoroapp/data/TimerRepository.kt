package com.pomodoroapp.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repository for managing Pomodoro timer state.
 * 
 * This class follows the repository pattern to centralize data management
 * and provide a clean API for the ViewModel layer.
 */
class TimerRepository {
    private val _pomodoroState = MutableStateFlow(PomodoroState())
    val pomodoroState: StateFlow<PomodoroState> = _pomodoroState.asStateFlow()

    /**
     * Updates the current Pomodoro state.
     */
    fun updateState(newState: PomodoroState) {
        _pomodoroState.value = newState
    }

    /**
     * Returns the current Pomodoro state.
     */
    fun getCurrentState(): PomodoroState = _pomodoroState.value

    /**
     * Determines the next session type based on the current session and completed count.
     * 
     * @param currentSession The current session type
     * @param completedSessions The number of completed work sessions
     * @return The next session type to transition to
     */
    fun getNextSession(currentSession: PomodoroSession, completedSessions: Int): PomodoroSession {
        return when (currentSession) {
            PomodoroSession.WORK -> {
                // After every 4th work session, take a long break
                if ((completedSessions + 1) % 4 == 0) {
                    PomodoroSession.LONG_BREAK
                } else {
                    PomodoroSession.SHORT_BREAK
                }
            }
            PomodoroSession.SHORT_BREAK, PomodoroSession.LONG_BREAK -> PomodoroSession.WORK
        }
    }
}