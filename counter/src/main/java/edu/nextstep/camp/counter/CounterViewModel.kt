package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    private val _lessThenZeroEvent = MutableLiveData<Event<Unit>>()
    val lessThenZeroEvent: LiveData<Event<Unit>>
        get() = _lessThenZeroEvent

    fun increaseCount() {
        var count = _count.value ?: return
        _count.value = ++count
    }

    fun decreaseCount() {
        var count = _count.value ?: return
        if (count <= 0) {
            _lessThenZeroEvent.value = Event(Unit)
            return
        }
        _count.value = --count
    }
}
