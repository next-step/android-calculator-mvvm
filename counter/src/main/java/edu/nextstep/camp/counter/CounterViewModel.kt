package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _counter = MutableLiveData(0)
    val counter = _counter as LiveData<Int>

    private val _sideEffect : MutableLiveData<Event<SideEffect>> = MutableLiveData(Event(SideEffect.None))
    val sideEffect = _sideEffect as LiveData<Event<SideEffect>>


    fun increase() {
        _counter.value = getCounterValue() + 1
    }

    fun decrease() {
        if (getCounterValue() == 0) {
            _sideEffect.value = Event(SideEffect.NegativeCounter)
            return
        }
        _counter.value = getCounterValue() - 1
    }

    private fun getCounterValue() = _counter.value ?: 0

    sealed class SideEffect {
        object NegativeCounter : SideEffect()
        object None : SideEffect()
    }
}
