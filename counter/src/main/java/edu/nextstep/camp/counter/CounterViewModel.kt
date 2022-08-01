package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(initialValue: Int = 0) : ViewModel() {

    private val _number = MutableLiveData(initialValue)
    val number: LiveData<Int>
        get() = _number

    private val _event = SingleLiveEvent<CounterEvent>()
    val event: LiveData<CounterEvent>
        get() = _event

    fun increase() {
        _number.value = _number.value?.plus(1)
    }

    fun decrease() {
        val number = _number.value ?: return
        if (number > 0) {
            _number.value = number - 1
        } else {
            _event.value = CounterEvent.ShowNegativeError
        }
    }
}
