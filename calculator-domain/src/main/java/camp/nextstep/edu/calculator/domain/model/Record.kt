package camp.nextstep.edu.calculator.domain.model

import camp.nextstep.edu.calculator.domain.Expression

data class Record(
    val expression: Expression,
    val result: Int
)