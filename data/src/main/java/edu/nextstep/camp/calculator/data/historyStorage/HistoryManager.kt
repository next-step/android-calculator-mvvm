package edu.nextstep.camp.calculator.data.historyStorage

internal class HistoryManager(
    private val historyDAO: HistoryDAO
) {
    suspend fun insert(expression: String, result: Int) {
        val history = HistoryEntity(
            expression = expression,
            result = result
        )

        historyDAO.insert(history)
    }

    suspend fun getAll(): List<HistoryEntity> {
        return historyDAO.getAll() ?: emptyList()
    }
}