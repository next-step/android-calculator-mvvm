package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(default: Int = 0) : ViewModel() {
    private val _count = MutableLiveData(default)
    val count: LiveData<Int> = _count

    private val _errorEvent = SingleLiveEvent<Unit>()
    val errorEvent: LiveData<Unit> = _errorEvent

    fun increase() {
        val count = _count.value ?: return
        _count.value = count + 1
    }

    fun decrease() {
        val count = getCount()
        if (count > 0) {
            _count.value = count - 1
        } else {
            _errorEvent.value = Unit
        }
    }

    private fun getCount(): Int {
        return _count.value ?: 0
    }
}
