package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    private val _count = MutableLiveData<Long>()
    val count: LiveData<Long>
        get() = _count

    private val _showDownZeroToast = SingleLiveEvent<Unit>()
    val showDownZeroToast: LiveData<Unit>
        get() = _showDownZeroToast

    init {
        _count.value = 0
    }

    fun up() {
        val value = _count.value ?: 0
        _count.value = value + 1
    }

    fun down() {
        val value = _count.value ?: 0
        if (value == 0L){
            _showDownZeroToast.value = Unit
            return
        }

        _count.value = value - 1
    }
}