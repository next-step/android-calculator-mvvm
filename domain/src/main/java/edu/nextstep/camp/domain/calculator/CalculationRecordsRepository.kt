package edu.nextstep.camp.domain.calculator

interface CalculationRecordsRepository {
    suspend fun saveCalculationRecord(vararg records: CalculationRecord)
    suspend fun loadCalculationRecords(): List<CalculationRecord>
}