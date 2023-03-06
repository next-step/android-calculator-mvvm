package camp.nextstep.edu.calculator.domain.repository

import camp.nextstep.edu.calculator.domain.model.Record

interface RecordRepository {
    suspend fun saveRecord(record: Record)
    suspend fun loadRecords(): List<Record>
}

