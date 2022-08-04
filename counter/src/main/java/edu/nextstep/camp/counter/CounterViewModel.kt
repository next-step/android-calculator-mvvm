package edu.nextstep.camp.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {

    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    private val _showToastEvent = MutableSharedFlow<String>()
    val showToastEvent = _showToastEvent.asSharedFlow()

    fun increment() = viewModelScope.launch {
        _count.emit(count.value + 1)
    }

    fun decrement() = viewModelScope.launch {
        if (count.value > 0) {
            _count.emit(count.value - 1)
        } else {
            showToast("0 이하로 내릴 수 없습니다")
        }
    }

    private fun showToast(message: String) = viewModelScope.launch {
        _showToastEvent.emit(message)
    }
}
