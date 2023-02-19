package camp.nextstep.edu.calculator.domain


import camp.nextstep.edu.calculator.domain.model.Record

interface RecordRepository {

    suspend fun insertRecord(record: Record)

    suspend fun deleteRecord(record: Record)

    suspend fun getRecord(): List<Record>
}