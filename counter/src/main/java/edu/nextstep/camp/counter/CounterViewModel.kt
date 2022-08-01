package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.domain.counter.Counter

class CounterViewModel(private var counter: Counter = Counter()) : ViewModel() {

    private val _onViewState = MutableLiveData<Event<CounterState>>()
    val onViewState: LiveData<Event<CounterState>> get() = _onViewState

    init {
        sendViewState(CounterState.EMPTY)
    }

    fun onEvent(event: CounterEvent) {
        when (event) {
            CounterEvent.Up -> eventUp()
            CounterEvent.Down -> eventDown()
        }
    }

    private fun eventUp() {
        counter.up().also {
            counter = it
            sendViewState(CounterState.Counted(it.number))
        }
    }

    private fun eventDown() {
        runCatching { counter.down().also { counter = it } }
            .onSuccess { sendViewState(CounterState.Counted(it.number)) }
            .onFailure { sendViewState(CounterState.ZeroDownError) }
    }

    private fun sendViewState(content: CounterState) {
        _onViewState.postValue(Event(content))
    }
}