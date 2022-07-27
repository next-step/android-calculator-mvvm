package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _counter: MutableLiveData<Int> = MutableLiveData(0)
    val counter: LiveData<Int> = _counter

    private val _event: MutableLiveData<Event<ViewEvent>> = MutableLiveData()
    val event: LiveData<Event<ViewEvent>> = _event

    fun increment() {
        _counter.value = getCurrentCount() + 1
    }

    fun decrement() {
        val currentCount = getCurrentCount()
        if (currentCount == 0) {
            _event.value = Event(ViewEvent.FailDecrement("0 이하로 내릴 수 없습니다"))
            return
        }
        _counter.value = currentCount - 1
    }

    private fun getCurrentCount(): Int {
        return _counter.value ?: 0
    }

    sealed class ViewEvent private constructor() {
        data class FailDecrement(val message: String) : ViewEvent()
    }
}