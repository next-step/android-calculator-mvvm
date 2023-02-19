package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData(0)

    val count: LiveData<Int> get() = _count

    private val _showToastMessage = MutableLiveData<Unit>()
    val showToastMessage: LiveData<Unit>
        get() = _showToastMessage

    fun upCount() {
        _count.value = _count.value?.plus(1)
    }

    fun downCount() {
        if (_count.value == 0) {
            _showToastMessage.value = Unit
            return
        }
        _count.value = _count.value?.minus(1)

    }
}

