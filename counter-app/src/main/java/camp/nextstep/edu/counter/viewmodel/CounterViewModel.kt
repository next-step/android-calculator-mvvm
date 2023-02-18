package camp.nextstep.edu.counter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.counter.model.Counter

class CounterViewModel : ViewModel() {
    private val _count: MutableLiveData<Int> = MutableLiveData(0)
    val count: LiveData<Int> = _count
    private var counter: Counter = Counter()

    fun up() {
        counter.add()
        _count.value = counter.value
    }

    fun down() {
        counter.sub()
        _count.value = counter.value

    }
}