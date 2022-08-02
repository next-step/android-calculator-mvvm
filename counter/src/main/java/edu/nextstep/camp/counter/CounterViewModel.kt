package edu.nextstep.camp.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {

    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    fun increment() = viewModelScope.launch {
        _count.emit(count.value + 1)
    }

    fun decrement() = viewModelScope.launch {
        if (count.value > 0) {
            _count.emit(count.value - 1)
        }
    }
}
