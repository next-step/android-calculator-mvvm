package camp.nextstep.edu.calculator.local.impl

import camp.nextstep.edu.calculator.domain.model.CalculatorResultData
import camp.nextstep.edu.calculator.domain.repository.CalculatorResultRepository
import camp.nextstep.edu.calculator.local.db.CalculatorDatabase

class CalculatorResultRepositoryImpl(private val db : CalculatorDatabase) : CalculatorResultRepository {
    override fun saveResult(calculatorResultData: CalculatorResultData) {
        TODO("Not yet implemented")
    }

    override fun getAllResult(): List<CalculatorResultData> {
        TODO("Not yet implemented")
    }
}