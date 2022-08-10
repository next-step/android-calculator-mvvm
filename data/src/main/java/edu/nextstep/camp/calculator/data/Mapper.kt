package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.History

internal fun CalculationHistoryEntity.toDomain(): History {
    return History(this.expression, this.result)
}

internal fun History.toEntity(): CalculationHistoryEntity {
    return CalculationHistoryEntity(this.expression, this.result)
}