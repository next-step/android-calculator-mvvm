package camp.nextstep.edu.counter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.counter.domain.Counter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _counter = MutableLiveData(Counter())
    val counter: LiveData<Counter> = _counter

    private val _uiState = MutableSharedFlow<CounterUiState>()
    val uiState = _uiState.asSharedFlow()

    fun increase() {
        val data = _counter.value ?: return
        _counter.value = data + 1
    }

    fun decrease() {
        val data = _counter.value ?: return

        runCatching {
            _counter.value = data - 1
        }.getOrElse {
            viewModelScope.launch {
                _uiState.emit(CounterUiState.Error( "0 이하로 내릴 수 없습니다"))
            }
        }
    }
}

sealed class CounterUiState {
    data class Error(val exception: String = ""): CounterUiState()
}
