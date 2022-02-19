package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _count = MutableLiveData(0)
    val count: LiveData<Int> get() = _count

    private val _eventShowErrorMessage = MutableLiveData<Event<Unit>>()
    val eventShowErrorMessage: LiveData<Event<Unit>> get() = _eventShowErrorMessage

    fun incrementCounter() {
        val count = _count.value ?: return
        _count.value = count + 1
    }

    fun decrementCounter() {
        val count = _count.value ?: return

        if(count == 0) {
            _eventShowErrorMessage.value = Event(Unit)
            return
        }

        _count.value = count - 1
    }
}
