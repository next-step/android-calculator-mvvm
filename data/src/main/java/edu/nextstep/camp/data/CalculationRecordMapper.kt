package edu.nextstep.camp.data

import edu.nextstep.camp.domain.calculator.CalculationRecord

internal object CalculationRecordMapper {
    fun mapToDomain(entity: CalculationRecordEntity): CalculationRecord = CalculationRecord(entity.expression, entity.result)
    fun mapToData(record: CalculationRecord): CalculationRecordEntity = CalculationRecordEntity(record.expression, record.result)
}