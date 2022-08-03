package edu.nextstep.camp.data

import edu.nextstep.camp.domain.calculator.CalculationRecord
import edu.nextstep.camp.domain.calculator.CalculationRecordsRepository

internal class DefaultCalculationRecordsRepository(
    private val calculationRecordsDao: CalculationRecordsDao,
    private val calculationRecordMapper: CalculationRecordMapper = CalculationRecordMapper
) : CalculationRecordsRepository {

    override suspend fun saveCalculationRecord(vararg records: CalculationRecord) {
        calculationRecordsDao.insertCalculationRecords(*records.map(calculationRecordMapper::mapToData).toTypedArray())
    }

    override suspend fun loadCalculationRecords(): List<CalculationRecord> {
        return calculationRecordsDao.loadCalculationRecords().map(calculationRecordMapper::mapToDomain).toList()
    }
}