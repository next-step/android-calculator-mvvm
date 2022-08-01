package edu.nextstep.camp.data

import edu.nextstep.camp.domain.calculator.CalculationRecord
import edu.nextstep.camp.domain.calculator.CalculationRecordsRepository

internal class CalculationRecordsRepositoryImpl(
    private val calculationRecordsDao: CalculationRecordsDao
) : CalculationRecordsRepository {

    override suspend fun saveCalculationRecord(vararg records: CalculationRecord) {
        calculationRecordsDao.insertCalculationRecords(*records.map(::mapTo).toTypedArray())
    }

    override suspend fun loadCalculationRecords(): List<CalculationRecord> {
        return calculationRecordsDao.loadCalculationRecords().map(::mapTo).toList()
    }

    private fun mapTo(calculationRecordEntity: CalculationRecordEntity): CalculationRecord = CalculationRecord(calculationRecordEntity.expression, calculationRecordEntity.result)
    private fun mapTo(calculationRecord: CalculationRecord): CalculationRecordEntity = CalculationRecordEntity(calculationRecord.expression, calculationRecord.result)
}