package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(
    private val countInitValue: Int = 0
): ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    private val _showErrorToast = SingleLiveEvent<Unit>()
    val showErrorToast: LiveData<Unit>
        get() = _showErrorToast

    init {
        _count.value = countInitValue
    }

    fun upCount() {
        _count.value = _count.value?.plus(1)
    }

    fun downCount() {
        if ((_count.value ?: 0) > 0) {
            _count.value = _count.value?.minus(1)
        } else {
            _showErrorToast.value = Unit
        }
    }
}