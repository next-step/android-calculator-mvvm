package edu.nextstep.camp.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.data.Injection
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> createCalculatorViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun createCalculatorViewModel(): CalculatorViewModel {
        val expression = Expression()
        val calculator = Calculator()
        val repository = Injection.historyRepository(context)
        return CalculatorViewModel(expression, calculator, repository)
    }
}