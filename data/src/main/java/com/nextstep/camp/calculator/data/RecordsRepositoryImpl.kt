package com.nextstep.camp.calculator.data

class RecordsRepositoryImpl(
    private val recordDao: RecordDao
) : RecordsRepository {

    override suspend fun getAll(): List<Record> = recordDao.getAll()

    override suspend fun insert(record: Record) = recordDao.insert(record)
}
