package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData(0)
        val count: LiveData<Int>
            get() = _count

    private val _showErrorMessage = MutableLiveData<Unit>()
        val showErrorMessage: LiveData<Unit>
            get() = _showErrorMessage

    fun countUp() {
        val count = _count.value ?: return
        _count.value = count + 1
    }

    fun countDown() {
        val count = _count.value ?: return
        if (count == 0) {
            _showErrorMessage.value = Unit
            return
        }
        _count.value = count - 1
    }
}