package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.counter.CounterEvent.*

class CounterViewModel(countValue: Int = 0) : ViewModel() {
    private val _count = MutableLiveData(countValue)
    val count: LiveData<Int>
        get() = _count

    private val _countEvent = SingleLiveEvent<Event<CounterEvent>>()
    val countEvent: LiveData<Event<CounterEvent>>
        get() = _countEvent

    fun increaseCount() {
        val newValue = (count.value ?: 0) + 1
        _count.value = newValue
    }

    fun decreaseCount() {
        if ((count.value ?: 0) == 0) {
            _countEvent.value = Event(CAN_NOT_DECREASE_COUNT_UNDER_0)
            return
        }
        _count.value = (count.value ?: 0) - 1
    }
}