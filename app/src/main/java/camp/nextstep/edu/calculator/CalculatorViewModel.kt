package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Calculator
import com.example.domain.OperationTerm
import com.example.domain.Statement

class CalculatorViewModel : ViewModel() {

    private val calculator = Calculator()
    private val statement = Statement()

    private val _text: MutableLiveData<String> = MutableLiveData("")
    val text: LiveData<String>
        get() = _text

    private val _exceptionMessage = SingleLiveEvent<String>()
    val exceptionMessage: LiveData<String>
        get() = _exceptionMessage

    fun addTerm(term: OperationTerm) {
        statement.addTerm(term)
        _text.value = statement.termsToString()
    }

    fun removeTerm() {
        statement.removeTerm()
        _text.value = statement.termsToString()
    }

    fun calculate() {
        try {
            val result = calculator.calculate(statement.termsToString())
            _text.value = result.toString()
        } catch (e: Throwable) {
            _exceptionMessage.value = e.message
        }
    }
}