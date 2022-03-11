package nextstep.edu.data

interface CalculatorRepository {
    fun addHistory(history: History)
    fun getHistoryList() : List<History>
}