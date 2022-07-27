package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(initialValue: Int? = null) : ViewModel() {

    private var number = initialValue ?: 0

    private val _liveNumber = MutableLiveData(number)
    val liveNumber: LiveData<Int>
        get() = _liveNumber

    fun increase() {
        number += 1
        _liveNumber.value = number
    }

    fun decrease() {
        TODO("Not yet implemented")
    }
}
