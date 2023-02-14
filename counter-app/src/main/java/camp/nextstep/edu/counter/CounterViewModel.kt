package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>(0)
    val count: LiveData<Int> get() = _count

    private val _isCounterLessThanZero = SingleLiveEvent<Unit>()
    val isCounterLessThanZero : LiveData<Unit> get() = _isCounterLessThanZero

    fun up() {
        val count = _count.value ?: 0
        _count.value = count + 1
    }

    fun down() {
        val count = _count.value ?: 0
        if (count <= 0) {
            _isCounterLessThanZero.call()
            return
        }

        _count.value = count - 1
    }
}