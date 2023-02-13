package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private var _upDownUiState = MutableLiveData<Int>()
    val upDownUiState: LiveData<Int> get() = _upDownUiState

    init {
        _upDownUiState.value = 0
    }

    fun increaseNumber() {
        _upDownUiState.value = _upDownUiState.value?.plus(1)
    }

    fun decreaseNumber() {
        if (upDownUiState.value == 0) {
            //TODO: 0미만은 안 된다는 토스트 호출
        }
        _upDownUiState.value = _upDownUiState.value?.minus(1)
    }
}