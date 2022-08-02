package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.nextstep.camp.calculator.data.repository.EvaluationRecordRepositoryImpl
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.EvaluationRecord
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.repository.EvaluationRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val evaluationRecordRepository: EvaluationRecordRepository,
    private val calculator: Calculator
    ): ViewModel() {
    private var expression = Expression.EMPTY

    private val _state = MutableLiveData<State>(State.ShowExpression(Expression.EMPTY))
    val state = _state as LiveData<State>

    private val _sideEffect : MutableLiveData<Event<SideEffect>> = MutableLiveData(Event(SideEffect.None))
    val sideEffect = _sideEffect as LiveData<Event<SideEffect>>

    fun addToExpression(operand: Int) {
        processExpressionBlock {
            _state.value = State.ShowExpression(it + operand)
        }
    }

    fun addToExpression(operator: Operator) {
        processExpressionBlock {
            _state.value = State.ShowExpression(it + operator)
        }
    }

    fun removeLast() {
        processExpressionBlock {
            _state.value = State.ShowExpression(it.removeLast())
        }
    }

    fun calculate() {
        processExpressionBlock { expression ->
            runCatching {
                calculator.calculate(expression.toString())
            }
            .onSuccess { result ->
                if (result == null) {
                    _sideEffect.value = Event(SideEffect.IncompleteExpressionError)
                } else {
                    _state.value = State.ShowExpression(Expression(listOf(result)))
                    viewModelScope.launch {
                        withContext(Dispatchers.IO) {
                            evaluationRecordRepository.record(EvaluationRecord(expression.toString(), result.toString()))
                        }
                    }
                }
            }
            .onFailure {
                if (it is ArithmeticException) {
                    _sideEffect.value = Event(SideEffect.DivideByZeroError)
                } else {
                    _sideEffect.value = Event(SideEffect.UnknownError)
                }
            }
        }
    }

    fun toggleHistoryBtn() {
        when (val currentState = _state.value) {
            is State.ShowExpression -> {
                expression = currentState.expression
//                _state.value = State.ShowHistory(evaluationRecordRepository.getEvaluationHistory())
            }
            is State.ShowHistory -> {
                _state.value = State.ShowExpression(expression)
            }
            else -> {}
        }
    }

    private inline fun processExpressionBlock(body: (Expression) -> Unit) {
        (_state.value as? State.ShowExpression)?.let {
            body.invoke(it.expression)
        }
    }

    sealed class SideEffect {
        object IncompleteExpressionError : SideEffect()
        object UnknownError : SideEffect()
        object DivideByZeroError : SideEffect()
        object None : SideEffect()
    }

    sealed class State {
        data class ShowExpression(val expression: Expression) : State()
        data class ShowHistory(val history: List<EvaluationRecord>) : State()
    }
}
