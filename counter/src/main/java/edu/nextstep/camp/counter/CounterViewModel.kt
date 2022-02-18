package edu.nextstep.camp.counter

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val defaultCount = 0

    private val _counter = MutableLiveData<Int>()

    private val _showToast = SingleLiveEvent<Unit>()
    val showToast: LiveData<Unit>
        get() = _showToast

    val counter: LiveData<Int>
        get() = _counter

    init {
        _counter.value = defaultCount
    }

    fun up() {
        _counter.value = counter.value?.plus(1)
    }

    fun down() {
        if (counter.value == 0) {
            _showToast.value = Unit
            return
        }
        _counter.value = counter.value?.minus(1)
    }

}