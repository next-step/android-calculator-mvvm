package camp.nextstep.edu.counter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.counter.SingleLiveEvent

class CounterViewModel : ViewModel() {

    private val _count = MutableLiveData<Int>(0)
    val count: LiveData<Int> = _count

    private val _negativeEvent = SingleLiveEvent<Unit>()
    val negativeEvent: LiveData<Unit> = _negativeEvent

    fun onClickUp() {
        _count.value = _count.value?.plus(1)
    }

    fun onClickDown() {
        if (_count.value == 0) _negativeEvent.call()
        else _count.value = _count.value?.minus(1)
    }
}