package edu.nextstep.camp.calculator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.data.di.CoroutinesModule
import edu.nextstep.camp.calculator.data.di.RepositoryModule
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.model.EvaluationRecord
import edu.nextstep.camp.calculator.domain.model.Expression
import edu.nextstep.camp.calculator.domain.model.Operator
import edu.nextstep.camp.calculator.domain.repository.EvaluationRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch

class CalculatorViewModel(application: Application) : ViewModel() {
    private val calculator = Calculator()
    private val evaluationRecordRepository: EvaluationRecordRepository =
        RepositoryModule.provideEvaluationRecordStoreRepository(context = application)

    private val _expressionState : MutableLiveData<Expression> = MutableLiveData(Expression.EMPTY)
    val expressionState : LiveData<Expression> = _expressionState

    val evaluationHistory = evaluationRecordRepository.getEvaluationHistory()

    private val _displayState = MutableLiveData<State>(State.ShowExpression)
    val displayState = _displayState as LiveData<State>

    private val _sideEffect : MutableLiveData<Event<SideEffect>> = MutableLiveData(Event(SideEffect.None))
    val sideEffect = _sideEffect as LiveData<Event<SideEffect>>

    fun addToExpression(operand: Int) {
        _expressionState.value = getExpressionStateValue() + operand
    }

    fun addToExpression(operator: Operator) {
        _expressionState.value = getExpressionStateValue() + operator
    }

    fun removeLast() {
        _expressionState.value = getExpressionStateValue().removeLast()
    }

    fun calculate() {
        runCatching {
            calculator.calculate(_expressionState.value.toString())
        }
        .onSuccess { result ->
            if (result == null) {
                _sideEffect.value = Event(SideEffect.IncompleteExpressionError)
            } else {
                viewModelScope.launch {
                    evaluationRecordRepository.record(EvaluationRecord(_expressionState.value.toString(), result.toString()))
                }
                _expressionState.value = Expression(listOf(result))
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

    fun toggleHistoryBtn() {
        when (_displayState.value) {
            State.ShowExpression -> {
                _displayState.value = State.ShowHistory
            }
            State.ShowHistory -> {
                _displayState.value = State.ShowExpression
            }
            else -> {}
        }
    }

    private fun getExpressionStateValue() : Expression = _expressionState.value ?: Expression.EMPTY

    sealed class SideEffect {
        object IncompleteExpressionError : SideEffect()
        object UnknownError : SideEffect()
        object DivideByZeroError : SideEffect()
        object None : SideEffect()
    }

    sealed class State {
        object ShowExpression : State()
        object ShowHistory : State()
    }
}

class CalculatorViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            return CalculatorViewModel(application) as T
        }
        throw IllegalArgumentException("Not found ViewModel class.")
    }
}
