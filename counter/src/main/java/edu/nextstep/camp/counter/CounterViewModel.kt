package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.counter.CounterEvent.*

class CounterViewModel(private var countValue: Int = 0) : ViewModel() {
    private val _count = MutableLiveData(countValue)
    val count: LiveData<Int>
        get() = _count

    private val _countEvent = SingleLiveEvent<CounterEvent>()
    val countEvent: LiveData<CounterEvent>
        get() = _countEvent

    fun increaseCount() {
        countValue++
        _count.value = countValue
    }

    fun decreaseCount() {
        if (countValue == 0) {
            _countEvent.value = CAN_NOT_DECREASE_COUNT_UNDER_0
            return
        }
        countValue--
        _count.value = countValue
    }
}