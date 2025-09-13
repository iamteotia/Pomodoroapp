package com.pomodoroapp.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.pomodoroapp.data.PomodoroSession
import com.pomodoroapp.data.PomodoroState
import com.pomodoroapp.data.TimerRepository
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel for the Pomodoro timer functionality.
 * 
 * This ViewModel manages the timer logic and exposes the UI state through
 * Kotlin Flow. It follows the MVVM architecture pattern.
 */
class PomodoroViewModel(
    private val repository: TimerRepository = TimerRepository()
) : ViewModel() {

    /**
     * Exposes the current Pomodoro state to the UI.
     */
    val pomodoroState: StateFlow<PomodoroState> = repository.pomodoroState

    private var countDownTimer: CountDownTimer? = null

    /**
     * Starts the Pomodoro timer.
     * 
     * Creates a new CountDownTimer that updates the state every second
     * until the session is complete.
     */
    fun startTimer() {
        val currentState = repository.getCurrentState()
        if (currentState.isRunning) return

        repository.updateState(currentState.copy(isRunning = true))

        countDownTimer = object : CountDownTimer(currentState.timeLeftMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val updatedState = repository.getCurrentState().copy(
                    timeLeftMillis = millisUntilFinished
                )
                repository.updateState(updatedState)
            }

            override fun onFinish() {
                completeSession()
            }
        }.start()
    }

    /**
     * Pauses the current timer.
     */
    fun pauseTimer() {
        countDownTimer?.cancel()
        val currentState = repository.getCurrentState()
        repository.updateState(currentState.copy(isRunning = false))
    }

    /**
     * Resets the current session to its full duration.
     */
    fun resetTimer() {
        countDownTimer?.cancel()
        val currentState = repository.getCurrentState()
        repository.updateState(
            currentState.copy(
                isRunning = false,
                timeLeftMillis = currentState.currentSession.getDurationMillis()
            )
        )
    }

    /**
     * Handles the completion of a session and transitions to the next one.
     */
    private fun completeSession() {
        val currentState = repository.getCurrentState()
        val nextSession = repository.getNextSession(
            currentState.currentSession, 
            currentState.completedSessions
        )

        val newCompletedCount = if (currentState.currentSession == PomodoroSession.WORK) {
            currentState.completedSessions + 1
        } else {
            currentState.completedSessions
        }

        repository.updateState(
            PomodoroState(
                currentSession = nextSession,
                timeLeftMillis = nextSession.getDurationMillis(),
                isRunning = false,
                completedSessions = newCompletedCount
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel()
    }
}