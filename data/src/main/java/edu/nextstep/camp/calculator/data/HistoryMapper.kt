package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.History

object HistoryMapper {
    fun getHistoryItem(historyEntity: HistoryEntity): History {
        return History(expression = historyEntity.expression, result = historyEntity.result)
    }

    fun getHistoryEntity(history: History): HistoryEntity {
        return HistoryEntity(expression = history.expression, result = history.result)
    }
}