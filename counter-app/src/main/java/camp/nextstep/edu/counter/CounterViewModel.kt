package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private var _upDownUiState = MutableLiveData<Int>()
    val upDownUiState: LiveData<Int> get() = _upDownUiState

    private var _stopDecreaseEvent = SingleLiveEvent<Unit>()
    val stopDecreaseEvent: LiveData<Unit> get() = _stopDecreaseEvent

    init {
        _upDownUiState.value = 0
    }

    fun increaseNumber() {
        _upDownUiState.value = _upDownUiState.value?.plus(1)
    }

    fun decreaseNumber() {
        if (_upDownUiState.value == 0) {
            _stopDecreaseEvent.call()
            return
        }

        _upDownUiState.value = _upDownUiState.value?.minus(1)
    }
}