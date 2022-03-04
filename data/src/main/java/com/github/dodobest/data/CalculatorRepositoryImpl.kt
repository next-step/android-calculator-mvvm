package com.github.dodobest.data

import com.github.dodobest.domain.CalculatorRepository
import com.github.dodobest.domain.ResultRecord

internal class CalculatorRepositoryImpl(
    private val database: AppDatabase
) : CalculatorRepository {
    override fun addMemory(resultRecord: ResultRecord) {
        database.resultRecordDao().insertResultRecord(ResultRecordMapper.mapperToResultRecordEntity(resultRecord))
    }

    override fun getMemories(): List<ResultRecord> {
        return database.resultRecordDao().getAll().map {
            ResultRecordMapper.mapperToResultRecord(it)
        }
    }
}