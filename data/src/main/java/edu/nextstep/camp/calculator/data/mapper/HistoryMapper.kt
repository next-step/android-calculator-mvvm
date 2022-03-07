package edu.nextstep.camp.calculator.data.mapper

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.repository.History
import  edu.nextstep.camp.calculator.data.local.History as LocalHistory

internal object HistoryMapper {
    fun History.toLocalData() =
        LocalHistory(
            this.expression.toString(),
            this.result.toString()
        )

    fun LocalHistory.toDomainData() =
        History(
            Expression(listOf(this.formula)),
            Expression(listOf(this.calculateResult))
        )
}