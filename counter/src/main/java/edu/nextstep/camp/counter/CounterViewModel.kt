package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(initialValue: Int = 0) : ViewModel() {

    private var number = initialValue

    private val _liveNumber = MutableLiveData(number)
    val liveNumber: LiveData<Int>
        get() = _liveNumber

    private val _liveEvent = SingleLiveEvent<Event<CounterEvent>>()
    val liveEvent: LiveData<Event<CounterEvent>>
        get() = _liveEvent

    fun increase() {
        number += 1
        _liveNumber.value = number
    }

    fun decrease() {
        if (number > 0) {
            number -= 1
            _liveNumber.value = number
        } else {
            _liveEvent.value = Event(CounterEvent.ShowNegativeError)
        }
    }
}
