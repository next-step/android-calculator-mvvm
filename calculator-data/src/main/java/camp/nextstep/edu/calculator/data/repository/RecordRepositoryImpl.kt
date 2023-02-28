package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.db.RecordDao
import camp.nextstep.edu.calculator.data.model.RecordEntity
import camp.nextstep.edu.calculator.domain.RecordRepository
import camp.nextstep.edu.calculator.domain.model.Record

internal class RecordRepositoryImpl(
    private val dao: RecordDao
) : RecordRepository {
    override suspend fun insertRecord(record: Record) {
        dao.insertRecord(RecordEntity.from(record))
    }

    override suspend fun deleteRecord(record: Record) {
        dao.deleteRecord(RecordEntity.from(record))
    }

    override suspend fun getRecords(): List<Record> {
        return dao.getRecords().map { it.toDomain() }
    }
}