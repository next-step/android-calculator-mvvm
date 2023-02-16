package camp.nextstep.edu.calculator.local.impl

import camp.nextstep.edu.calculator.domain.model.CalculatorResultData
import camp.nextstep.edu.calculator.domain.repository.CalculatorResultRepository
import camp.nextstep.edu.calculator.local.dao.CalculatorResultDao
import camp.nextstep.edu.calculator.local.entity.CalculatorResultEntity

class CalculatorResultRepositoryImpl(
    private val dao: CalculatorResultDao,
) : CalculatorResultRepository {
    override fun saveResult(calculatorResultData: CalculatorResultData) {
        dao.save(
            CalculatorResultEntity(
                expression = calculatorResultData.expression,
                answer = calculatorResultData.answer
            )
        )
    }

    override fun getAllResult(): List<CalculatorResultData> {
        return dao.getResultList().map {
            CalculatorResultData(
                expression = it.expression,
                answer = it.answer
            )
        }
    }
}