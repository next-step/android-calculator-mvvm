package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    private var _counter = MutableLiveData<String>()
    val counter: LiveData<String>
        get() = _counter

    private var _toastEvent = SingleLiveEvent<String>()
    val toastEvent: LiveData<String>
        get() = _toastEvent

    init {
        _counter.value = "0"
    }

    fun clickUpButton() {
        val currentCounter = _counter.value.toString()
        val newCounter = currentCounter.toInt() + 1

        _counter.value = newCounter.toString()
    }

    fun clickDownButton() {
        if(_counter.value.equals("0")) {
            _toastEvent.value = "0 이하로 내릴 수 없습니다"
        } else {
            val currentCounter = _counter.value.toString()
            val newCounter = currentCounter.toInt() - 1

            _counter.value = newCounter.toString()
        }
    }
}