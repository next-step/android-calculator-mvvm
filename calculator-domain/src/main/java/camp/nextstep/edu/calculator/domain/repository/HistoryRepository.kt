package camp.nextstep.edu.calculator.domain.repository

import camp.nextstep.edu.calculator.domain.model.History

interface HistoryRepository {

    suspend fun insertHistory(history: History)

    suspend fun deleteHistory(history: History)

    suspend fun getHistories(): List<History>
}