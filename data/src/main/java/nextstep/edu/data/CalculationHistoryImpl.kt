package nextstep.edu.data

class CalculationHistoryImpl(
    private val historyDao: HistoryDao
) : CalculatorRepository {
    override fun addHistory(history: History) {
        historyDao.insertHistory(history)
    }

    override fun getHistoryList(): List<History> {
        return historyDao.getAll()
    }
}