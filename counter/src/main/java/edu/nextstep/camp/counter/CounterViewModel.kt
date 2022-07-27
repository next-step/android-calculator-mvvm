package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    private val _toastMessage = MutableLiveData("")
    val toastMessage: LiveData<String>
        get() = _toastMessage

    fun increment() {
        _count.value = _count.value?.plus(1)
    }

    fun decrement() {
        _count.value?.minus(1)?.let { currentCount ->
            if (currentCount > 0) {
                _count.value = currentCount
            } else {
                _toastMessage.value = ERROR_MESSAGE
            }
        }
    }

    companion object {
        const val ERROR_MESSAGE = "0 이하로 내릴 수 없습니다."
    }
}