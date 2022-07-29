package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.domain.counter.Counter

class CounterViewModel(private var counter: Counter = Counter()) : ViewModel() {

    private val _onViewState = MutableLiveData<Event<Any>>()
    val onViewState: LiveData<Event<Any>>
        get() = _onViewState

    init {
        sendViewState(CounterViewState.EMPTY)
    }

    fun onEvent(event: CounterViewEvent) {
        when (event) {
            CounterViewEvent.Up -> eventUp()
            CounterViewEvent.Down -> eventDown()
        }
    }

    private fun eventUp() {
        counter.up().also {
            counter = it
            sendViewState(CounterViewState.Counted(it.number))
        }
    }

    private fun eventDown() {
        runCatching { counter.down().also { counter = it } }
            .onSuccess { sendViewState(CounterViewState.Counted(it.number)) }
            .onFailure { sendViewState(CounterViewState.ZeroDownError) }
    }

    private fun sendViewState(content: Any) {
        _onViewState.postValue(Event(content))
    }
}