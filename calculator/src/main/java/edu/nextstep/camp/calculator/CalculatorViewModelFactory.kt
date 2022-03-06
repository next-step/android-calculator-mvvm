package edu.nextstep.camp.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.dodobest.data.Injector
import com.github.dodobest.domain.Calculator
import com.github.dodobest.domain.Expression
import com.github.dodobest.domain.usecase.AddMemoryUseCase
import com.github.dodobest.domain.usecase.GetMemoriesUseCase

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
        val calculatorRepository = Injector.provideCalculatorRepository(context)
        val addMemoryUseCase = AddMemoryUseCase(calculatorRepository)
        val getMemoryUseCase = GetMemoriesUseCase(calculatorRepository)
        return CalculatorViewModel(expression, calculator, addMemoryUseCase, getMemoryUseCase)
    }
}