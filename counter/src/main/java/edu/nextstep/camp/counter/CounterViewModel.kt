package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(count: Int = 0) : ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    val errorMessage = SingleLiveEvent<Event<Unit>>()

    init {
        _count.value = count
    }

    fun increment() {
        val originCount = count.value ?: 0
        _count.value = originCount + 1
    }

    fun decrement() {
        val originCount = count.value ?: 0
        if (originCount > 0) {
            _count.value = originCount - 1
            return
        }
        errorMessage.call()
    }
}
