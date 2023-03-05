package camp.nextstep.edu.calculator.domain.repository

import camp.nextstep.edu.calculator.domain.model.Record

interface RecordRepository {
    fun saveRecord(record: Record)
    fun loadRecords(): List<Record>
}

