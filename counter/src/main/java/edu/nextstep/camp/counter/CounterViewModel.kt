package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(initialValue: Int = 0) : ViewModel() {

    private val _number = MutableLiveData(initialValue)
    val number: LiveData<Int>
        get() = _number

    private val _event = SingleLiveEvent<Event<CounterEvent>>()
    val event: LiveData<Event<CounterEvent>>
        get() = _event

    fun increase() {
        _number.value?.let { number ->
            _number.value = number + 1
        }
    }

    fun decrease() {
        _number.value?.let { number ->
            if (number > 0) {
                _number.value = number - 1
            } else {
                _event.value = Event(CounterEvent.ShowNegativeError)
            }
        }
    }
}
