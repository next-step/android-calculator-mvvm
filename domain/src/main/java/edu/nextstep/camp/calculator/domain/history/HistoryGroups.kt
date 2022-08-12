package edu.nextstep.camp.calculator.domain.history

class HistoryGroups(
    private val historyList: List<History> = emptyList()
) {
    fun getHistoryList() = historyList
}