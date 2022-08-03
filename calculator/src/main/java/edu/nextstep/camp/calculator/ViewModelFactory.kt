package edu.nextstep.camp.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.data.CalculationHistoryDatabase
import java.lang.IllegalArgumentException

class ViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val newViewModel =
            when (modelClass) {
                CalculatorViewModel::class.java -> createCalculatorViewModel(applicationContext)
                else -> IllegalArgumentException()
            } as T
        return newViewModel
    }

    private fun createCalculatorViewModel(applicationContext: Context): CalculatorViewModel =
        CalculatorViewModel(
            calculationHistoryDB = CalculationHistoryDatabase.create(applicationContext)
        )
}