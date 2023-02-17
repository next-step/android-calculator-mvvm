package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private var _upDownText = MutableLiveData<Int>()
    val upDownText: LiveData<Int> get() = _upDownText

    private var _stopDecreaseEvent = SingleLiveEvent<Unit>()
    val stopDecreaseEvent: LiveData<Unit> get() = _stopDecreaseEvent

    init {
        _upDownText.value = 0
    }

    fun increaseNumber() {
        _upDownText.value = _upDownText.value?.plus(1)
    }

    fun decreaseNumber() {
        if (_upDownText.value == 0) {
            _stopDecreaseEvent.call()
            return
        }

        _upDownText.value = _upDownText.value?.minus(1)
    }
}