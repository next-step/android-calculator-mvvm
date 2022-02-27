package com.github.dodobest.data

internal class CalculatorRepositoryImpl(
    private val database: AppDatabase
) : CalculatorRepository {
    override fun addMemory(resultRecord: ResultRecord) {
        database.resultRecordDao().insertResultRecord(resultRecord)
    }

    override fun getMemories(): MutableList<ResultRecord> {
        return database.resultRecordDao().getAll()
    }
}