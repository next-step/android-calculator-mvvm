package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(initialValue: Int? = null) : ViewModel() {

    private val _liveNumber = MutableLiveData(initialValue ?: 0)
    val liveNumber: LiveData<Int>
        get() = _liveNumber

    fun increase() {
        // TODO
    }
}
