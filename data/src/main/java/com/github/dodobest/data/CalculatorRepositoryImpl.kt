package com.github.dodobest.data

import com.github.dodobest.domain.CalculatorRepository

internal class CalculatorRepositoryImpl(
    private val database: AppDatabase
) : CalculatorRepository<ResultRecord> {
    override fun addMemory(resultRecord: ResultRecord) {
        database.resultRecordDao().insertResultRecord(resultRecord)
    }

    override fun getMemories(): List<ResultRecord> {
        return database.resultRecordDao().getAll().toList()
    }
}