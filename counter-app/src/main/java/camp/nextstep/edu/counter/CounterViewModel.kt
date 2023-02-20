package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _count = MutableLiveData(0)
    val count: LiveData<String> = Transformations.map(_count) { it.toString() }

    private val _onEvent = SingleLiveEvent<Any>()
    val onEvent: LiveData<Any>
        get() = _onEvent

    fun plusCount() {
        _count.postValue(_count.value?.inc())
    }

    fun minusCount() {
        val currentCount = _count.value ?: 0
        if (currentCount > 0) {
            _count.postValue(_count.value?.dec())
        } else {
            noticeCannotMinus()
        }
    }

    private fun noticeCannotMinus() {
        _onEvent.call()
    }
}
