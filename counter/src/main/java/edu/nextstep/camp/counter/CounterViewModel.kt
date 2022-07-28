package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(initialValue: Int = 0) : ViewModel() {

    private val _liveNumber = MutableLiveData(initialValue)
    val liveNumber: LiveData<Int>
        get() = _liveNumber

    private val _liveEvent = SingleLiveEvent<Event<CounterEvent>>()
    val liveEvent: LiveData<Event<CounterEvent>>
        get() = _liveEvent

    fun increase() {
        _liveNumber.value = _liveNumber.value!! + 1
    }

    fun decrease() {
        if (_liveNumber.value!! > 0) {
            _liveNumber.value = _liveNumber.value!! - 1
        } else {
            _liveEvent.value = Event(CounterEvent.ShowNegativeError)
        }
    }
}
