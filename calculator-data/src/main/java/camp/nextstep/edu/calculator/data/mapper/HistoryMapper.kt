package camp.nextstep.edu.calculator.data.mapper

import camp.nextstep.edu.calculator.data.database.HistoryEntity
import camp.nextstep.edu.calculator.domain.model.History

fun HistoryEntity.toDomain(): History {
    return History(
        statement = this.statement ?: "",
        result = this.result
    )
}

fun History.toData(): HistoryEntity {
    return HistoryEntity(
        statement = this.statement,
        result = this.result
    )
}