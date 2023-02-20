package camp.nextstep.edu.calculator.domain.repository

import camp.nextstep.edu.calculator.domain.model.CalculatorResult


interface ResultRepository {

    fun saveResult(result: CalculatorResult)

    fun getAllResults(): List<CalculatorResult>
}
