package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _count: MutableLiveData<Int> = SingleLiveEvent()
    val count: LiveData<Int>
        get() = _count

    private val _event: MutableLiveData<Event<String>> = SingleLiveEvent()
    val event: LiveData<Event<String>>
        get() = _event

    init {
        _count.value = 0
    }

    fun reqUpCount() {
        _count.value = _count.value?.plus(1)
    }

    fun reqDownCount() {
        require(_count.value != null)

        if (_count.value == 0) {
            _event.value = Event(Constants.EVENT_SHOW_TOAST)
        } else {
            _count.value = _count.value?.minus(1)
        }
    }
}
