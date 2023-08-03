package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _number: MutableLiveData<Int> = MutableLiveData(0)
    val number: LiveData<Int> = _number

    private val _numberUnderZero: MutableLiveData<Event<Unit>> = MutableLiveData()
    val numberUnderZero: LiveData<Event<Unit>> = _numberUnderZero

    fun numberPlusOne() {
        _number.value = _number.value?.plus(1)
    }

    fun numberMinusOne() {
        if (number.value == NUM_ZERO_DIVIDER) {
            _numberUnderZero.value = Event(Unit)
            return
        }
        _number.value = _number.value?.minus(1)
    }


    companion object {
        const val NUM_ZERO_DIVIDER = 0
    }
}