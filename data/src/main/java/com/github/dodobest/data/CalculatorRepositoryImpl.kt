package com.github.dodobest.data

import com.github.dodobest.domain.CalculatorRepository

internal class CalculatorRepositoryImpl(
    private val database: AppDatabase
) : CalculatorRepository {
    override fun addMemory(expression: String, result: String) {
        database.resultRecordDao().insertResultRecord(ResultRecord(expression, result))
    }

    override fun getMemories(): MutableList<ResultRecord> {
        return database.resultRecordDao().getAll()
    }
}