package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    private val _countIsZeroEvent = SingleLiveEvent<Unit>()
    val countIsZeroEvent: LiveData<Unit>
        get() = _countIsZeroEvent

    fun upCount() {
        _count.value?.let {
            _count.value = it + 1
        }
    }

    fun downCount() {
        if (_count.value == 0) {
            _countIsZeroEvent.value = Unit
            return
        }
        _count.value?.let {
            _count.value = it - 1
        }
    }
}