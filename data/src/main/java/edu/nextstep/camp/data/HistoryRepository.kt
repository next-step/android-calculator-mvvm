package edu.nextstep.camp.data

/**
 * Created by link.js on 2022. 07. 31..
 */
class HistoryRepository(
    private val historyDao: HistoryDao,
) {
    suspend fun setHistories(list: List<History>) {
        historyDao.insertAll(list)
    }

    suspend fun getHistories(): List<History> {
        return historyDao.getAll()
    }
}