package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.db.RecordDatabase
import camp.nextstep.edu.calculator.data.model.RecordEntity
import camp.nextstep.edu.calculator.domain.model.Record

class RecordRepositoryImpl(
    private val db: RecordDatabase
) : camp.nextstep.edu.calculator.domain.RecordRepository {
    override suspend fun insertRecord(record: Record) {
        db.recordDao().insertRecord(RecordEntity.from(record))
    }

    override suspend fun deleteRecord(record: Record) {
        db.recordDao().deleteRecord(RecordEntity.from(record))
    }

    override suspend fun getRecord(): List<Record> {
        return db.recordDao().getRecords().map { it.toDomain() }
    }
}