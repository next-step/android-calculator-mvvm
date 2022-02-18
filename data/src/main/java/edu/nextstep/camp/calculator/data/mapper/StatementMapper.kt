package edu.nextstep.camp.calculator.data.mapper

import edu.nextstep.camp.calculator.data.model.Statement
import edu.nextstep.camp.calculator.domain.model.RecordStatement

internal fun RecordStatement.map() = Statement(
    this.uuid,
    this.expression,
    this.calculateResult
)
