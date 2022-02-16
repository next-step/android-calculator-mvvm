package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int> = _count

    private val _errorEvent = MutableLiveData<Event<Unit>>()
    val errorEvent: LiveData<Event<Unit>> = _errorEvent

    fun increase() {
        val count = _count.value ?: return
        _count.value = count + 1
    }

    fun decrease() {
        val count = _count.value ?: return
        if (count <= 0) {
            _errorEvent.value = Event(Unit)
            return
        }
        _count.value = count - 1
    }
}
