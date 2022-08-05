package edu.nextstep.camp.calculator.data.historyStorage

class HistoryManager(
    private val historyDatabase: HistoryDatabase?
) {
    suspend fun insert(expression: String, result: Int) {
        val history = HistoryEntity(
            expression = expression,
            result = result
        )

        historyDatabase?.historyDao()?.insert(history)
    }

    suspend fun getAll(): List<HistoryEntity> {
        return historyDatabase?.historyDao()?.getAll() ?: emptyList()
    }
}