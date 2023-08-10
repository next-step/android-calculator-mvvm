package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {

	private val calculator = Calculator()

	private val _expression: MutableLiveData<Expression> = MutableLiveData(Expression.EMPTY)
	val expression: LiveData<Expression>
		get() = _expression

	private val _expressionInCompleted: MutableLiveData<Unit> = SingleLiveEvent()
	val expressionInCompleted: LiveData<Unit>
		get() = _expressionInCompleted

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
			_expression.value = Expression(listOf(result))
		}
	}

	fun removeLast() {
		val expressionSnapshot = _expression.value ?: return

		_expression.value = expressionSnapshot.removeLast()
	}
}