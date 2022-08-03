package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(initialValue: Int = 0) : ViewModel() {
    private val _count = MutableLiveData(initialValue)
    val count: LiveData<Int>
        get() = _count
    private val _showErrorMessage = MutableLiveData<Event<Any>>()
    val showErrorMessage: LiveData<Event<Any>>
        get() = _showErrorMessage


    fun up() {
        val count = _count.value ?: return
        _count.value = count + 1
    }

    fun down() {
        val count = _count.value ?: return
        if (count == 0) {
            _showErrorMessage.value = Event<Any>("0 이하로 내려갈 수 없습니다.")
            return
        }
        _count.value = count - 1
    }
}