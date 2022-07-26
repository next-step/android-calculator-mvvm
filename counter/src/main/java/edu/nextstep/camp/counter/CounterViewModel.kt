package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _count = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    private val _countFailed = SingleLiveEvent<Boolean>()
    val countFailed: LiveData<Boolean>
        get() = _countFailed

    fun increase() {
        _count.value = _count.value?.plus(COUNT_UNIT)
    }

    fun decrease() {
        if (count.value == MIN_COUNT) {
            _countFailed.value = true
        } else {
            _count.value = _count.value?.minus(COUNT_UNIT)
        }
    }

    companion object {
        private const val COUNT_UNIT = 1
        private const val MIN_COUNT = 0
    }
}
