package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * CounterActivity에 대한 viewModel
 * Created by jeongjinhong on 2022. 07. 27..
 */
class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    private var _errorEvent = SingleLiveEvent<Unit>()
    val errorEvent: LiveData<Unit>
        get() = _errorEvent

    fun increment() {
        val originalCount = count.value ?: 0
        _count.value = originalCount + 1
    }

    fun decrement() {
        val originalCount = count.value ?: 0
        if (originalCount <= 0) {
            _errorEvent.call()
            return
        }
        _count.value = originalCount - 1
    }

}