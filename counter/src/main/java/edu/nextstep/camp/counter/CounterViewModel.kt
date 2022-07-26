package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _count = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    fun increase() {
        _count.value = _count.value?.plus(COUNT_UNIT)
    }

    fun decrease() {
        _count.value = _count.value?.minus(COUNT_UNIT)
    }

    companion object {
        private const val COUNT_UNIT = 1
    }
}
