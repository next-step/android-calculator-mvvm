package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CounterViewModel(private val counter: Counter) : ViewModel() {

    private val _number = MutableLiveData(counter.number)
    val number: LiveData<Int> = _number

    private val _showWarningMessageEvent = SingleLiveEvent<String>()
    val showWarningMessageEvent: LiveData<String> = _showWarningMessageEvent

    fun incrementCounter() {
        counter.increment()
        _number.value = counter.number
    }

    fun decrementCounter() {
        kotlin.runCatching {
            counter.decrement()
        }.onSuccess {
            _number.value = counter.number
        }.onFailure {
            showWarningMessage(it.message ?: "")
        }
    }

    private fun showWarningMessage(message: String) {
        _showWarningMessageEvent.value = message
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
