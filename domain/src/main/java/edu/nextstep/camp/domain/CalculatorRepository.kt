package edu.nextstep.camp.domain

interface CalculatorRepository {
    fun addHistory(calculatorHistory: CalculatorHistoryData)

    fun getHistory(): List<CalculatorHistoryData>
}