package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _count: MutableLiveData<Int> = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    private val _event: MutableLiveData<Event<EventType>> = SingleLiveEvent()
    val event: LiveData<Event<EventType>>
        get() = _event

    fun upCount() {
        _count.value = _count.value?.plus(1)
    }

    fun downCount() {
        require(_count.value != null)

        if (_count.value == 0) {
            _event.value = Event(EventType.SHOW_TOAST)
        } else {
            _count.value = _count.value?.minus(1)
        }
    }

    enum class EventType {
        SHOW_TOAST
    }
}
