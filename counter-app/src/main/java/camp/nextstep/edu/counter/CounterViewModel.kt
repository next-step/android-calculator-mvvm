package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private var _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    private var _isCountNegative = SingleLiveEvent<Boolean>()
    val isCountNegative
        get() = _isCountNegative

    init {
        _count.value = 0
    }

    fun upCount() {
        val currentCount = _count.value ?: 0
        _count.value = currentCount + 1
    }

    fun downCount() {
        val currentCount = _count.value ?: 0

        if (isCurrentCountZero(currentCount)) {
            _isCountNegative.value = true
        } else {
            _count.value = currentCount - 1
        }
    }

    private fun isCurrentCountZero(currentCount: Int): Boolean {
        return currentCount == 0
    }
}