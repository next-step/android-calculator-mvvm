package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private val _result = MutableLiveData("")
        val result: LiveData<String>
            get() = _result

    fun num(clickedNumber: Int) {
        val result = _result.value ?: return
        _result.value = clickedNumber.toString()
    }

    fun plus() {

    }

    fun minus() {

    }

    fun multiply() {

    }

    fun divide() {

    }

    fun erase() {}

    fun equals() {}

    fun memory() {}
}