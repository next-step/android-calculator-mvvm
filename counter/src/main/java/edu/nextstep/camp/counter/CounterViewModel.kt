package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.domain.counter.Counter
import edu.nextstep.camp.domain.counter.NegativeCountNotSupported

class CounterViewModel(
    private val counter: Counter
) : ViewModel() {
    private val _count = MutableLiveData(counter.currentCount)
    val count: LiveData<Int> get() = _count

    private val _errorMessage = SingleLiveEvent<UiText>()
    val errorMessage: LiveData<UiText> get() = _errorMessage

    fun countUp() {
        _count.value = counter.countUp()
    }

    fun countDown() {
        runCatching {
            _count.value = counter.countDown()
        }.onFailure {
            showErrorMessage(it)
        }
    }

    private fun showErrorMessage(it: Throwable) {
        if (it is NegativeCountNotSupported) {
            _errorMessage.value = UiText.StringResource(R.string.negative_count_not_supported)
        }
    }
}

class CounterViewModelFactory(private var counter: Counter): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CounterViewModel::class.java)) {
            return CounterViewModel(counter) as T
        }
        throw IllegalArgumentException("Not found ViewModel class.")
    }
}