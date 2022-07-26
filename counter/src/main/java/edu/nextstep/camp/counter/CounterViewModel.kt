package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int>
        get() = _counter

    private val _countFailed = SingleLiveEvent<Boolean>()
    val countFailed: LiveData<Boolean>
        get() = _countFailed

    fun increase() {
        _counter.value = _counter.value?.plus(COUNT_UNIT)
    }

    fun decrease() {
        if (counter.value == MIN_COUNT) {
            _countFailed.value = true
        } else {
            _counter.value = _counter.value?.minus(COUNT_UNIT)
        }
    }

    companion object {
        private const val COUNT_UNIT = 1
        private const val MIN_COUNT = 0
    }
}
