package camp.nextstep.edu.counter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.counter.SingleLiveEvent
import camp.nextstep.edu.counter.model.Counter

class CounterViewModel : ViewModel() {
    private var counter: Counter = Counter()
    private val _count: MutableLiveData<Int> = MutableLiveData(0)
    val count: LiveData<Int> = _count
    private val _downError: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val downError: LiveData<Boolean> = _downError

    fun up() {
        counter.add()
        _count.value = counter.value
    }

    fun down() {
        if (!counter.sub()) {
            _downError.call()
            return
        }
        _count.value = counter.value
    }
}