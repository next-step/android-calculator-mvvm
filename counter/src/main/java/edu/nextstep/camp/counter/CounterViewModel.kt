package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(initialCount: Int = 0) : ViewModel() {
    private val _count = MutableLiveData(initialCount)
    val count: LiveData<Int>
        get() = _count

    private val _lessThenZeroEvent = MutableLiveData<Event<Boolean>>()
    val lessThenZeroEvent: LiveData<Event<Boolean>>
        get() = _lessThenZeroEvent

    fun increaseCount() {
        var count = _count.value ?: return
        _count.value = ++count
    }

    fun decreaseCount() {
        var count = _count.value ?: return
        if (count <= 0) {
            _lessThenZeroEvent.value = Event(true)
            return
        }
        _count.value = --count
    }
}
