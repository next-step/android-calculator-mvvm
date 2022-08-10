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
    private val _counterUiState = MutableLiveData(CounterUiState(counter.currentCount, null))
    val counterUiState: LiveData<CounterUiState> get() = _counterUiState

    fun countUp() {
        _counterUiState.value = _counterUiState.value?.copy(
            count = counter.countUp()
        )
    }

    fun countDown() {
        try {
            tryCountDown()
        } catch (e: NegativeCountNotSupported) {
            showNegativeCountNotSupportedErrorMessage()
        }
    }

    private fun tryCountDown() {
        _counterUiState.value = _counterUiState.value?.copy(
            count = counter.countDown()
        )
    }

    private fun showNegativeCountNotSupportedErrorMessage() {
        _counterUiState.value = _counterUiState.value?.copy(
            errorMessage = UiText.StringResource(R.string.negative_count_not_supported)
        )
    }

    fun shownErrorMessage() {
        _counterUiState.value = _counterUiState.value?.copy(
            errorMessage = null
        )
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