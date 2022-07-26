package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    fun increment() {
        _count.value = _count.value?.plus(1)
    }

    fun decrement() {
        val currentCount = _count.value ?: 0
        if (currentCount > 0) {
            _count.value = currentCount - 1
        }
    }
}