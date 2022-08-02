package edu.nextstep.camp.data

import edu.nextstep.camp.domain.calculator.CalculationRecord
import edu.nextstep.camp.domain.calculator.CalculationRecordsRepository

internal class DefaultCalculationRecordsRepository(
    private val calculationRecordsDao: CalculationRecordsDao
) : CalculationRecordsRepository {

    override suspend fun saveCalculationRecord(vararg records: CalculationRecord) {
        calculationRecordsDao.insertCalculationRecords(*records.map(CalculationRecordEntity::of).toTypedArray())
    }

    override suspend fun loadCalculationRecords(): List<CalculationRecord> {
        return calculationRecordsDao.loadCalculationRecords().map(CalculationRecordEntity::mapToDomain).toList()
    }
}