package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.data.model.CalculateResultEntity
import edu.nextstep.camp.calculator.domain.CalculateResult
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

fun CalculateResultEntity.toCalculateResult(): CalculateResult {
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

    return CalculateResult(expression = expression, result = this.result)
}
