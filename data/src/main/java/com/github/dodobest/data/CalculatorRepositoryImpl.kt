package com.github.dodobest.data

import com.github.dodobest.domain.CalculatorRepository
import com.github.dodobest.domain.ResultRecord

internal class CalculatorRepositoryImpl(
    private val database: AppDatabase
) : CalculatorRepository {
    override fun addMemory(resultRecord: ResultRecord) {
        database.resultRecordDao().insertResultRecord(resultRecord.toEntity())
    }

    override fun getMemories(): List<ResultRecord> {
        return database.resultRecordDao().getAll().map {
            it.toDomain()
        }
    }
}