package edu.nextstep.camp.calculator.data

/**
 * Created by link.js on 2022. 07. 31..
 */
class HistoryRepository(
    private val historyDao: HistoryDao,
) {
    suspend fun setHistories(list: List<History>) {
        historyDao.setHistories(list)
    }

    suspend fun getHistories(): List<History> {
        return historyDao.getAll()
    }
}