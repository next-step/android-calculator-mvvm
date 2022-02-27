package edu.nextstep.camp.calculator

import android.content.Context
import edu.nextstep.camp.calculator.local.CalculatorDatabase

class CalculatorRepositoryProvider {
    companion object {
        private var instance: CalculateRepository? = null

        fun getInstance(context: Context): CalculateRepository {
            return instance ?: buildCalculateStorage(context).also { instance = it }
        }

        private fun buildCalculateStorage(context: Context): CalculateRepository {
            val historyDao = CalculatorDatabase.getInstance(context).getHistoryDao()
            return LocalCalculateRepository(historyDao)
        }
    }
}