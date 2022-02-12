package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private var _count = MutableLiveData("0")
    val count: LiveData<String>
        get() = _count

    private val _isMinusCount = MutableLiveData(false)
    val isMinusCount: MutableLiveData<Boolean>
        get() = _isMinusCount

    fun upCount() {
        _count.value = count.value?.toInt()?.plus(1).toString()
    }

    fun downCount() {
        if (count.value == "0") {
            _isMinusCount.value = true
            return
        }
        _count.value = count.value?.toInt()?.minus(1).toString()
    }
}