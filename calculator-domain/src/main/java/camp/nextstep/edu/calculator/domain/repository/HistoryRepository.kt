package camp.nextstep.edu.calculator.domain.repository

import camp.nextstep.edu.calculator.domain.model.History

interface HistoryRepository {
    fun insertHistory(history: History)

    fun getHistories(): List<History>
}