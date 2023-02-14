package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData(0)

    val count: LiveData<Int> get() = _count

    fun upCount() {
        _count.value = _count.value?.plus(1)
    }

    fun downCount(): Result {
        if (_count.value == 0) {
            return Result(false, "count must be positive number")
        }
        _count.value = _count.value?.minus(1)
        return Result(true, "")
    }
}

