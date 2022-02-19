package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.repository.MemoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val memoryRepository: MemoryRepository
) : ViewModel() {

    private val calculator = Calculator()

    val eventShowIncompleteExpressionError = SingleLiveEvent<Unit>()

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> = _expression

    private fun getCurrentExpression() = _expression.value ?: Expression.EMPTY

    private val _isVisibleMemoryList = MutableLiveData(false)
    val isVisibleMemoryList: LiveData<Boolean> = _isVisibleMemoryList

    val memoryFlow: Flow<List<Memory>> = memoryRepository.getAllMemory()

    fun addToExpression(operand: Int) {
        _expression.postValue(getCurrentExpression().plus(operand))
    }

    fun addToExpression(operator: Operator) {
        _expression.postValue(getCurrentExpression().plus(operator))
    }

    fun calculate() {
        val result = calculator.calculate(getCurrentExpression().toString())
        if (result == null) {
            showIncompleteExpressionError()
            return
        }
        _expression.postValue(Expression(listOf(result)))
        saveExpressionToMemory(result)
    }

    fun removeLast() {
        _expression.value?.let {
            _expression.postValue(it.removeLast())
        }
    }

    fun showAndHideMemoryListVisible() {
        isVisibleMemoryList.value?.let {
            _isVisibleMemoryList.postValue(it.not())
        }
    }

    private fun showIncompleteExpressionError() {
        eventShowIncompleteExpressionError.call()
    }

    private fun saveExpressionToMemory(result: Int) {
        viewModelScope.launch {
            val expression = getCurrentExpression().toString()
            memoryRepository.insert(Memory(expression, result))
        }
    }
}