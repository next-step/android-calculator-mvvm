package nextstep.edu.data

interface CalculationHistoryRepository {
    fun addHistory(history: History)
    fun getHistoryList() : List<History>
}