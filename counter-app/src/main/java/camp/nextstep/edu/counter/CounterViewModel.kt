package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CounterViewModel : ViewModel() {

    private val _count = MutableLiveData(0)
    val count: LiveData<Int> get() = _count

    private val _warning = SingleLiveEvent<Unit>()
    val warning: LiveData<Unit> get() = _warning


    fun onClickUp() {
        _count.value = _count.value?.plus(1)
    }

    fun onClickDown() {
        val count = _count.value ?: 0
        when {
            count <= 0 -> {
                _warning.call()
            }
            else -> {
                _count.value = count - 1
            }
        }
    }
}
