package edu.nextstep.camp.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.data.di.Injector

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> createCalculatorViewModel(context)
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun createCalculatorViewModel(context: Context): CalculatorViewModel = CalculatorViewModel(
        Calculator(),
        Injector.provideCalculatorRepository(context)
    )
}