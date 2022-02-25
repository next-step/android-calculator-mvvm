package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(defaultCount: Int) : ViewModel() {

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter

    private val _errorToast = SingleLiveEvent<Boolean>()
    val errorToast: LiveData<Boolean>
        get() = _errorToast

    init {
        _counter.value = defaultCount
    }

    fun up() {
        _counter.value = counter.value?.plus(1)
    }

    fun down() {
        if (counter.value == 0) {
            _errorToast.value = true
            return
        }
        _counter.value = counter.value?.minus(1)
    }

}