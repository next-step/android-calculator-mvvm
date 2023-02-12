package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count: MutableLiveData<Int> = MutableLiveData(0)

    val count: LiveData<Int>
        get() = _count

    fun countUp() {
        val nowCount = _count.value ?: 0
        _count.value = nowCount + 1
    }

    fun countDown() {
        val nowCount = _count.value ?: 0
        _count.value = nowCount - 1
    }
}