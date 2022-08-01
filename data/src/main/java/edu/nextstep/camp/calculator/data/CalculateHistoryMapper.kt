package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.data.model.CalculateHistoryEntity
import edu.nextstep.camp.calculator.domain.CalculateHistory
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

fun CalculateHistoryEntity.toCalculateHistory(): CalculateHistory {
    val list = mutableListOf<Any>()

    this.expression.split(" ").forEach {
        val operator = Operator.of(it)

        if (operator != null) {
            list.add(operator)
        } else {
            list.add(it.toInt())
        }
    }

    val expression = Expression(list)

    return CalculateHistory(expression = expression, result = this.result)
}
