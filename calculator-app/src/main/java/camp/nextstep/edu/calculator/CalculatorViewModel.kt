package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.RecordRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CalculatorViewModel(private val recordRepository: RecordRepository, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

	private val calculator = Calculator()

	private val _expression: MutableLiveData<Expression> = MutableLiveData(Expression.EMPTY)
	val expression: LiveData<Expression>
		get() = _expression

	private val _expressionInCompleted: MutableLiveData<Unit> = SingleLiveEvent()
	val expressionInCompleted: LiveData<Unit>
		get() = _expressionInCompleted

	private val _showRecord: MutableLiveData<Boolean> = MutableLiveData(false)
	val showRecord: LiveData<Boolean>
		get() = _showRecord

	val records = recordRepository.getAll()
		.stateIn(
			scope = viewModelScope,
			started = SharingStarted.WhileSubscribed(5_000L),
			initialValue = emptyList()
		)

	fun addToExpression(operand: Int) {
		val expressionSnapshot = _expression.value ?: return

		_expression.value = expressionSnapshot + operand
	}

	fun addToExpression(operator: Operator) {
		val expressionSnapshot = _expression.value ?: return

		_expression.value = expressionSnapshot + operator
	}

	fun calculate() {
		val expressionSnapshot = _expression.value ?: return

		val result = calculator.calculate(expressionSnapshot.toString())
		if (result == null) {
			_expressionInCompleted.value = Unit
		} else {
			insertRecord(expressionSnapshot, result)
			_expression.value = Expression(listOf(result))
		}
	}

	private fun insertRecord(expression: Expression, result: Int) = viewModelScope.launch(ioDispatcher) {
		recordRepository.insert(expression, result)
	}

	fun removeLast() {
		val expressionSnapshot = _expression.value ?: return

		_expression.value = expressionSnapshot.removeLast()
	}

	fun toggleRecord() {
		val showRecordSnapshot = _showRecord.value ?: return

		_showRecord.value = !showRecordSnapshot
	}
}