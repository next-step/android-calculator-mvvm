package camp.nextstep.edu.calculator.local.impl

import camp.nextstep.edu.calculator.domain.model.CalculatorResultData
import camp.nextstep.edu.calculator.domain.repository.CalculatorResultRepository
import camp.nextstep.edu.calculator.local.dao.CalculatorResultDao
import camp.nextstep.edu.calculator.local.entity.CalculatorResultEntity
import java.util.concurrent.ExecutorService

class CalculatorResultRepositoryImpl(
    private val dao: CalculatorResultDao,
    private val executorService: ExecutorService,
) : CalculatorResultRepository {
    override fun saveResult(calculatorResultData: CalculatorResultData) {
        executorService.submit {
            dao.save(
                CalculatorResultEntity(
                    expression = calculatorResultData.expression,
                    answer = calculatorResultData.answer
                )
            )
        }

    }

    override fun getAllResult(): List<CalculatorResultData> {
        return executorService.submit<List<CalculatorResultData>> {
            dao.getResultList().map {
                CalculatorResultData(
                    expression = it.expression,
                    answer = it.answer
                )
            }
        }.get()
    }
}