package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    private val _expressionEvent = SingleLiveEvent<Expression>()
    val expressionEvent: SingleLiveEvent<Expression> get() = _expressionEvent

    private val _errorEvent = SingleLiveEvent<Void>()
    val errorEvent: LiveData<Void> get() = _errorEvent

    init {
        _expressionEvent.value = Expression.EMPTY
    }
}