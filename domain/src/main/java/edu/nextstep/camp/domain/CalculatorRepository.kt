package edu.nextstep.camp.domain

interface CalculatorRepository {
    fun addHistory(calculatorHistory: CalculatorHistory)

    fun getHistory(): List<CalculatorHistory>
}