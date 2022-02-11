package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(initialCounter: Int = 0) : ViewModel() {

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter
    
    private val _showErrorMessage = SingleLiveEvent<Unit>()
    val showErrorMessage: LiveData<Unit>
        get() = _showErrorMessage

    init {
        _counter.value = initialCounter
    }

    fun up() {
        val count = _counter.value ?: return
        _counter.value = count + 1
    }

    fun down() {
        val count = _counter.value ?: return
        if (count == 0) {
            _showErrorMessage.value = Unit
            return
        }

        _counter.value = count - 1
    }
}