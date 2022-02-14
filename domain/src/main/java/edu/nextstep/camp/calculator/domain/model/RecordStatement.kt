package edu.nextstep.camp.calculator.domain.model

import java.util.UUID

data class RecordStatement(
    val uuid: UUID = UUID.randomUUID(),
    val expression: String,
    val calculateResult: CalculateResult
)
