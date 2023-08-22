package camp.nextstep.edu.calculator.domain.repo

interface HistoryRepository {

    suspend fun addHistory(history: History)

    suspend fun loadHistories(): List<History>
}
