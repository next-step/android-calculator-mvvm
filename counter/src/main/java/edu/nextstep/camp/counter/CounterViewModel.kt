package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    private val _downFail = MutableLiveData<Unit>()
    val downFail: LiveData<Unit>
        get() = _downFail

    fun upCount() {
        val originCount = _count.value ?: 0
        _count.value = originCount + 1
    }

    fun downCount() {
        val originCount = _count.value ?: 0

        if (originCount <= 0) {
            _downFail.value = Unit
            return
        }
        _count.value = originCount - 1
    }
}
