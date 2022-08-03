package com.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.Record

internal fun RecordEntity.toDomain() = Record(
    expression = expression,
    result = result
)

internal fun Record.toEntity() = RecordEntity(
    expression = expression,
    result = result
)
