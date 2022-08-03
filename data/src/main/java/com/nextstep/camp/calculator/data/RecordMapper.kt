package com.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.Record

fun RecordEntity.toDomain() = Record(
    expression = expression,
    result = result
)

fun Record.toEntity() = RecordEntity(
    expression = expression,
    result = result
)
