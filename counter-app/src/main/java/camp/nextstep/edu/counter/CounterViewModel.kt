package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _number: MutableLiveData<Int> = MutableLiveData(0)
    val number: LiveData<Int> = _number

    private val _underZeroToastMessage: MutableLiveData<Event<Unit>> = MutableLiveData()
    val underZeroToastMessage: LiveData<Event<Unit>> = _underZeroToastMessage

    fun onUpButtonClicked() {
        _number.value = _number.value?.plus(1)
    }

    fun onDownButtonClicked() {
        if (number.value == 0) {
            _underZeroToastMessage.value = Event(Unit)
            return
        }
        _number.value = _number.value?.minus(1)
    }


}