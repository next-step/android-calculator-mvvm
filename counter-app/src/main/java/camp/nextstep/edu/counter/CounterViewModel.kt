package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(initCounter: Int = 0): ViewModel() {
    private var _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter

    private var _toastEvent = SingleLiveEvent<String>()
    val toastEvent: LiveData<String>
        get() = _toastEvent

    init {
        _counter.value = initCounter
    }

    fun clickUpButton() {
        val currentCounter = _counter.value.toString()
        val newCounter = currentCounter.toInt() + 1

        _counter.value = newCounter
    }

    fun clickDownButton() {
        if(_counter.value == 0) {
            _toastEvent.value = "0 이하로 내릴 수 없습니다"
        } else {
            val currentCounter = _counter.value.toString()
            val newCounter = currentCounter.toInt() - 1

            _counter.value = newCounter
        }
    }
}
