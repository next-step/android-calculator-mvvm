package camp.nextstep.edu.counter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.counter.SingleLiveEvent
import camp.nextstep.edu.counter.domain.Counter

class MainViewModel : ViewModel() {

    private val _counter = MutableLiveData(Counter.ZERO)
    val counter: LiveData<Counter> = _counter

    private val _uiState = SingleLiveEvent<CounterUiState>()
    val uiState: LiveData<CounterUiState> = _uiState
    fun increase() {
        val data = _counter.value ?: return
        _counter.value = data + 1
    }

    fun decrease() {
        val data = _counter.value ?: return

        runCatching {
            _counter.value = data - 1
        }.onFailure {
            _uiState.call()
        }
    }
}

sealed class CounterUiState {
    data class Error(val exception: String = "") : CounterUiState()
}
