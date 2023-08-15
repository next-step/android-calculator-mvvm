package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CounterViewModel(private val counter: Counter) : ViewModel() {

    private val _text = MutableLiveData(counter.number.toString())
    val text: LiveData<String> = _text

    private val _showToastEvent = SingleLiveEvent<String>()
    val showToastEvent: LiveData<String> = _showToastEvent

    fun clickUpButton() {
        counter.increment()
        _text.value = counter.number.toString()
    }

    fun clickDownButton() {
        kotlin.runCatching {
            counter.decrement()
        }.onSuccess {
            _text.value = counter.number.toString()
        }.onFailure {
            showToast(it.message ?: "")
        }
    }

    private fun showToast(message: String) {
        _showToastEvent.value = message
    }
}

class CounterViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CounterViewModel::class.java -> CounterViewModel(Counter())
            else -> throw IllegalStateException()
        } as T
    }
}