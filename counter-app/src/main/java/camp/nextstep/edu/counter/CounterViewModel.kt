package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _count: MutableLiveData<Int> = MutableLiveData(0)
    val count: LiveData<Int> = _count

    private val _countUnderZero: MutableLiveData<Event<Unit>> = MutableLiveData()
    val countUnderZero: LiveData<Event<Unit>> = _countUnderZero

    fun countPlusOne() {
        _count.value = _count.value?.plus(1)
    }

    fun countMinusOne() {
        if (count.value == COUNT_ZERO) {
            _countUnderZero.value = Event(Unit)
            return
        }
        _count.value = _count.value?.minus(1)
    }


    companion object {
        const val COUNT_ZERO = 0
    }
}