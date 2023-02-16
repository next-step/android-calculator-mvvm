package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>(0)
    val count: LiveData<Int> = _count

    private val _onError: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val onError: LiveData<Boolean> = _onError

    fun upCount() {
        val count = _count.value!!
        _count.value = count.plus(1)
    }

    fun downCount() {
        val count = _count.value!!
        if (_count.value!! > 0) {
            _count.value = count.minus(1)
        } else {
            _onError.call()
        }
    }
}
