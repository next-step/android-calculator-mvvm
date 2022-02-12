package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private var _count = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    private val _isMinusCount = MutableLiveData(false)
    val isMinusCount: MutableLiveData<Boolean>
        get() = _isMinusCount

    fun upCount() {
        _count.value = count.value?.plus(1)
    }

    fun downCount() {
        if (count.value == 0) {
            _isMinusCount.value = true
            return
        }
        _count.value = count.value?.minus(1)
    }
}