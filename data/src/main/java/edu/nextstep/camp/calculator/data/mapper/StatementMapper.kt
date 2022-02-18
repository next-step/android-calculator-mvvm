package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.data.model.Statement
import edu.nextstep.camp.calculator.domain.model.RecordStatement

fun Statement.map() = RecordStatement(
    this.uuid,
    this.expression,
    this.calculateResult
)

fun RecordStatement.map() = Statement(
    this.uuid,
    this.expression,
    this.calculateResult
)
