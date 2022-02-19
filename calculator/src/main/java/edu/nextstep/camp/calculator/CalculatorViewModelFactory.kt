package edu.nextstep.camp.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.data.AppDatabase
import edu.nextstep.camp.calculator.data.Memory
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> createCalculatorViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun createCalculatorViewModel(): CalculatorViewModel {
        val expression = Expression()
        val calculator = Calculator()
        val database = AppDatabase.getInstance(context)
        val memory = Memory(database.resultRecordDao().getAll())
        return CalculatorViewModel(expression, calculator, database, memory)
    }
}