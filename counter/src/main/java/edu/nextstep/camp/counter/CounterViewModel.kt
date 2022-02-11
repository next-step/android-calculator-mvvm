package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.IllegalStateException

class CounterViewModel: ViewModel() {
    private val _count: MutableLiveData<Counter> = MutableLiveData(Counter(0))
    val count: LiveData<Counter>
        get() = _count
    private val counter: Counter get() = _count.value ?: Counter(0)

    private val _countIsZeroEvent = SingleLiveEvent<Unit>()
    val countIsZeroEvent: LiveData<Unit>
        get() = _countIsZeroEvent

    fun upCount() {
        _count.value = counter.plusCount()
    }

    fun downCount() {
        try {
            check (counter.number > 0)
            _count.value = counter.minusCount()
        }
        catch (e: IllegalStateException) {
            _countIsZeroEvent.value = Unit
        }
    }
}