package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    fun upCount() {
        val originCount = _count.value ?: 0
        _count.value = originCount + 1
    }
}
