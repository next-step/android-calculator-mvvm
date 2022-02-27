package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.data.local.History

interface CalculateRepository {
    val historyAll: List<History>
    fun save(history: History)
}