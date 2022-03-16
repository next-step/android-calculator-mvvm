package edu.nextstep.camp.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.domain.Calculator
import nextstep.edu.data.Injector

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> createCalculatorViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun createCalculatorViewModel(): CalculatorViewModel {
        return CalculatorViewModel(
            Calculator(),
            Injector.provideHistoryRepository(context)
        )
    }
}