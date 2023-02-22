package camp.nextstep.edu.calculator

import androidx.lifecycle.*
import com.example.domain.models.*
import com.example.domain.usecases.GetHistoriesUseCase
import kotlinx.coroutines.launch

class CalculatorViewModel(
    terms: List<OperationTerm> = listOf(),
    private val calculator: Calculator = CalculatorApplication.calculator,
    getHistoriesUseCase: GetHistoriesUseCase = CalculatorApplication.getHistoriesUseCase
) : ViewModel() {
    private val statement = Statement(terms.toMutableList())

    private val _text: MutableLiveData<String> = MutableLiveData(statement.termsToString())
    val text: LiveData<String>
        get() = _text

    val histories: LiveData<List<History>> = getHistoriesUseCase().asLiveData()

    private val _exceptionMessage = SingleLiveEvent<String>()
    val exceptionMessage: LiveData<String>
        get() = _exceptionMessage

    private val _showHistory: MutableLiveData<Boolean> = MutableLiveData(false)
    val showHistory: LiveData<Boolean>
        get() = _showHistory

    fun addTerm(term: OperationTerm) {
        statement.addTerm(term)
        _text.value = statement.termsToString()
    }

    fun addTerm(term: Int) {
        try {
            statement.addTerm(Operand(term))
            _text.value = statement.termsToString()
        } catch (e: Throwable) {
            _exceptionMessage.value = e.message
        }
    }

    fun removeTerm() {
        statement.removeTerm()
        _text.value = statement.termsToString()
    }

    fun calculate() {
        viewModelScope.launch {
            try {
                val result = calculator.calculate(statement.termsToString())
                _text.value = result.toString()
            } catch (e: Throwable) {
                _exceptionMessage.value = e.message
            }
        }
    }

    fun toggleHistory() {
        _showHistory.value = !(showHistory.value ?: false)
    }
}

class CalculatorViewModelFactory(
    private val calculator: Calculator,
    private val getHistoriesUseCase: GetHistoriesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> CalculatorViewModel(
                calculator = calculator,
                getHistoriesUseCase = getHistoriesUseCase
            )
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }
}
