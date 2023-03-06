package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.database.RecordDao
import camp.nextstep.edu.calculator.data.database.RecordEntity
import camp.nextstep.edu.calculator.domain.model.Record
import camp.nextstep.edu.calculator.domain.repository.RecordRepository

class RecordRepositoryImpl(private val recordDao: RecordDao) : RecordRepository {
    override suspend fun saveRecord(record: Record) {
        recordDao.saveRecord(RecordEntity.create(record))
    }

    override suspend fun loadRecords(): List<Record> {
        return recordDao.loadRecords().map { it.toRecord() }
    }
}
