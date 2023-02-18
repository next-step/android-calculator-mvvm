package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Calculator
import com.example.domain.models.Operand
import com.example.domain.models.OperationTerm
import com.example.domain.models.Statement

class CalculatorViewModel(terms: List<OperationTerm> = listOf()) : ViewModel() {

    private val calculator = Calculator()
    private val statement = Statement(terms.toMutableList())

    private val _text: MutableLiveData<String> = MutableLiveData(statement.termsToString())
    val text: LiveData<String>
        get() = _text

    private val _exceptionMessage = SingleLiveEvent<String>()
    val exceptionMessage: LiveData<String>
        get() = _exceptionMessage

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
        try {
            val result = calculator.calculate(statement.termsToString())
            _text.value = result.toString()
        } catch (e: Throwable) {
            _exceptionMessage.value = e.message
        }
    }
}
