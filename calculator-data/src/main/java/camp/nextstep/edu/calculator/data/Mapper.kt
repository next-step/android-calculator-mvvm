package camp.nextstep.edu.calculator.data

import camp.nextstep.edu.calculator.data.db.entity.ResultEntity
import camp.nextstep.edu.calculator.domain.model.CalculatorResult


fun CalculatorResult.toEntity() =
    ResultEntity(
        expression = this.expression,
        answer = this.result
    )

fun ResultEntity.toDto() =
    CalculatorResult(
        expression = this.expression,
        result = this.answer
    )