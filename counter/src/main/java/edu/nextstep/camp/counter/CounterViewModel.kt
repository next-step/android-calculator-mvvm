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

    private val _errorEvent = MutableSharedFlow<Throwable>()
    val errorEvent = _errorEvent.asSharedFlow()

    fun increment() {
        _count.value = count.value + 1
    }

    fun decrement() = viewModelScope.launch {
        if (count.value > 0) {
            _count.value = count.value - 1
        } else {
            _errorEvent.emit(Throwable(DecrementMinimumValueException()))
        }
    }
}
