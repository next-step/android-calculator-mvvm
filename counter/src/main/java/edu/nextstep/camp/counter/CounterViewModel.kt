package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(private val initCount: Int = 0) : ViewModel() {
    private val _count = MutableLiveData(initCount)
    val count: LiveData<Int>
        get() = _count

    private val _errorEvent = SingleLiveEvent<ErrorEvent>()
    val errorEvent: LiveData<ErrorEvent>
        get() = _errorEvent

    private fun getCounterValue() = _count.value ?: initCount

    fun increment() {
        val originCount = getCounterValue()
        _count.value = originCount + 1
    }

    fun decrement() {
        val originCount = getCounterValue()
        if (originCount > 0) {
            _count.value = originCount - 1
            return
        }
        _errorEvent.value = ErrorEvent.CalculatorError
    }
}
