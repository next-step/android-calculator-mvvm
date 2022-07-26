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
        val cur = _counter.value ?: 0
        _counter.value = cur + 1
    }

    fun decrement() {
        val cur = _counter.value ?: 0

        if (cur == 0) {
            _event.value = Event(ViewEvent.FailDecrement("0 이하로 내릴 수 없습니다"))
            return
        }

        _counter.value = cur - 1
    }

    sealed class ViewEvent private constructor() {
        data class FailDecrement(val message: String) : ViewEvent()
    }
}