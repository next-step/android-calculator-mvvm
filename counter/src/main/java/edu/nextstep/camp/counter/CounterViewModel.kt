package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(initialNumber: Int = 0) : ViewModel() {

    private val _countEvent = SingleLiveEvent<Int>()
    val countEvent: LiveData<Int> get() = _countEvent

    private val _errorEvent = SingleLiveEvent<Void>()
    val errorEvent: LiveData<Void> get() = _errorEvent

    init {
        _countEvent.value = initialNumber
    }

    fun up() {
        val value = _countEvent.value ?: 0
        _countEvent.value = value + 1
    }

    fun down() {
        val value = _countEvent.value ?: 0
        if (value <= 0) {
            _errorEvent.call()
            return
        }

        _countEvent.value = value - 1
    }
}