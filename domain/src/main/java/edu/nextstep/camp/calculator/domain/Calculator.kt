package edu.nextstep.camp.calculator.domain

class Calculator(
    private val historyRepository: HistoryRepository,
    historyList: List<History> = listOf(),
) {

    var historyList: List<History> = historyList
        private set

    fun calculate(rawExpression: String): Int? {
        if (rawExpression.isBlank()) return null

        val values = rawExpression.split(" ")
        if (values.size % 2 == 0) return null

        var acc = values[0].toIntOrNull() ?: return null
        for (i in 1..values.lastIndex step 2) {
            val operator = Operator.of(values[i]) ?: return null
            val secondOperand = values[i + 1].toIntOrNull() ?: return null
            acc = operator.operation(acc, secondOperand)
        }

        historyList += History(rawExpression, acc)

        return acc
    }

    suspend fun saveHistory(): Result<Unit> {
        return historyRepository.save(historyList)
    }

    suspend fun loadHistory() {
        historyRepository.fetch().onSuccess {
            historyList = it
        }
    }
}
