package edu.nextstep.camp.calculator.data

import android.content.Context
import edu.nextstep.camp.calculator.data.local.CalculatorDatabase
import edu.nextstep.camp.calculator.domain.repository.CalculateRepository

object CalculatorRepositoryProvider {
    private var instance: CalculateRepository? = null

    fun getInstance(context: Context): CalculateRepository {
        return instance ?: buildCalculateStorage(context).also { instance = it }
    }

    private fun buildCalculateStorage(context: Context): CalculateRepository {
        val historyDao = CalculatorDatabase.getInstance(context).getHistoryDao()
        return LocalCalculateRepository(historyDao)
    }
}