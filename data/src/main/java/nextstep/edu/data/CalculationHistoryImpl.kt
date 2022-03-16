package nextstep.edu.data

internal class CalculationHistoryImpl(
    private val historyDao: HistoryDao
) : CalculationHistoryRepository {
    override fun addHistory(history: History) {
        historyDao.insertHistory(history)
    }

    override fun getHistoryList(): List<History> {
        return historyDao.getAll()
    }
}