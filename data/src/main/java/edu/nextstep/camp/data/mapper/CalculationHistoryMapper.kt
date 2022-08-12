package edu.nextstep.camp.data.mapper

import edu.nextstep.camp.data.local.entity.CalculationHistoryEntity
import edu.nextstep.camp.domain.calculator.CalculationHistory
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator

fun CalculationHistoryEntity.toCalculationHistory(): CalculationHistory {
    var expression = Expression.EMPTY
    expressionText.split(" ").forEach {
        when {
            it.first().isDigit() -> expression += it.toInt()
            else -> expression += Operator.valueOf(it)
        }
    }

    return CalculationHistory(
        id = id ?: CalculationHistory.INVALID_ID,
        expression = expression,
        result = result
    )
}

fun CalculationHistory.toCalculationHistoryEntity(): CalculationHistoryEntity {

    val entityId = if (id == CalculationHistory.INVALID_ID) {
        null
    } else {
        id
    }

    return CalculationHistoryEntity(
        id = entityId,
        expressionText = expression.toString(),
        result = result
    )
}