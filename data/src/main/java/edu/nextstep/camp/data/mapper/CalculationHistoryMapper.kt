package edu.nextstep.camp.data.mapper

import edu.nextstep.camp.data.local.entity.CalculationHistoryEntity
import edu.nextstep.camp.domain.calculator.CalculationHistory
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator

fun CalculationHistoryEntity.toCalculationHistory(): CalculationHistory {
    return CalculationHistory(
        expressionText = expressionText,
        result = result
    )
}

fun CalculationHistory.toCalculationHistoryEntity(): CalculationHistoryEntity {
    return CalculationHistoryEntity(
        expressionText = expressionText,
        result = result
    )
}