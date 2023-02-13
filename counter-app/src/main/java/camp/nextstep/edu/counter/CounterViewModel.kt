package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private var _upDownUiState = MutableLiveData<Int>()
    val upDownUiState: LiveData<Int> get() = _upDownUiState

    private var _checkLessThanZero = SingleLiveEvent<Boolean>()
    val checkLessThanZero: LiveData<Boolean> get() = _checkLessThanZero

    init {
        _upDownUiState.value = 0
    }

    fun increaseNumber() {
        _upDownUiState.value = _upDownUiState.value?.plus(1)
    }

    fun decreaseNumber() {
        if (_upDownUiState.value == 0) {
            _checkLessThanZero.value = true
            return
        }

        _upDownUiState.value = _upDownUiState.value?.minus(1)
    }
}