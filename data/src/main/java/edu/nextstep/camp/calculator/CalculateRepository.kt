package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.local.History

interface CalculateRepository {
    val historyAll: List<History>
    fun save(history: History)
}