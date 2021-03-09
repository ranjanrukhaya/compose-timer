package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TimerViewModel : ViewModel() {

    private var job: Job? = null
    private val _timerValue = MutableStateFlow(900)
    val timerValue = _timerValue.asStateFlow()

    private val _play = MutableStateFlow(false)
    val play = _play.asStateFlow()

    fun start(times: Int = 900) {
        if (_timerValue.value == 0) _timerValue.value = times
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            delay(timeMillis = 200)
            while (isActive) {
                if (_timerValue.value <= 0) {
                    job?.cancel()
                    _play.value = false
                    return@launch
                }
                delay(timeMillis = 50)
                _timerValue.value -= 1
                _play.value = true
            }
        }
    }

    fun pause() {
        job?.cancel()
        _play.value = false
    }

    fun stop() {
        job?.cancel()
        _timerValue.value = 0
        _play.value = true
    }

    fun restart() {
        stop()
        start()
    }
}