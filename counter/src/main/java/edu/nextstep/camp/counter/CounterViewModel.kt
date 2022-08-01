package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.counter.event.Event

class CounterViewModel: ViewModel() {
    private val _count = MutableLiveData<Long>()
    val count: LiveData<Long> = _count

    private val _showEvent = SingleLiveEvent<Event>()
    val showEvent: LiveData<Event>
        get() = _showEvent

    init {
        _count.value = 0
    }

    fun up() {
        val value = current()
        _count.value = value + 1
    }

    fun down() {
        val value = current()
        if (value == 0L){
            _showEvent.value = Event.Error("0 이하로 내릴 수 없습니다")
            return
        }

        _count.value = value - 1
    }

    private fun current() = _count.value ?: 0
}