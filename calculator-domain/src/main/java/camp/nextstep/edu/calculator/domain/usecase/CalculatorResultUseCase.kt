package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.model.CalculatorResultData
import camp.nextstep.edu.calculator.domain.repository.CalculatorResultRepository

class CalculatorResultUseCase(
    private val repository: CalculatorResultRepository,
) {
    fun invoke(): List<CalculatorResultData> {
        return repository.getAllResult()
    }
}