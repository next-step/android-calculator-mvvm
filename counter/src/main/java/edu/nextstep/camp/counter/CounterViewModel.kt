package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(testDependencyInitValue: Int) : ViewModel() {

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter

    init {
        _counter.value = testDependencyInitValue
    }
}