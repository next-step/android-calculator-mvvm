package edu.nextstep.camp.data.mapper

import edu.nextstep.camp.data.local.entity.CalculationHistoryEntity
import edu.nextstep.camp.domain.calculator.CalculationHistory

fun CalculationHistoryEntity.toCalculationHistory(): CalculationHistory {
    return CalculationHistory(
        id = id ?: CalculationHistory.DEFAULT_ID,
        expressionText = expressionText,
        result = result
    )
}

fun CalculationHistory.toCalculationHistoryEntity(): CalculationHistoryEntity {
    return CalculationHistoryEntity(
        id = if (id == CalculationHistory.DEFAULT_ID) {
            null
        } else {
            id
        },
        expressionText = expressionText,
        result = result
    )
}