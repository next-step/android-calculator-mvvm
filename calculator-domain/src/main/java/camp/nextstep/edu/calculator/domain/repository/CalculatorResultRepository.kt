package camp.nextstep.edu.calculator.domain.repository

import camp.nextstep.edu.calculator.domain.model.CalculatorResultData


interface CalculatorResultRepository {
    fun saveResult(calculatorResultData: CalculatorResultData)
    fun getAllResult(): List<CalculatorResultData>?
}