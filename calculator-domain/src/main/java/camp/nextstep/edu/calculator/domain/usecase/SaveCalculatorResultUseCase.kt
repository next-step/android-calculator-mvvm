package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.model.CalculatorResultData
import camp.nextstep.edu.calculator.domain.repository.CalculatorResultRepository
import kotlin.math.exp

class SaveCalculatorResultUseCase(
    private val repository: CalculatorResultRepository,
) {
    operator fun invoke(expression: String, result: Int) {
        repository.saveResult(CalculatorResultData(expression, result))
    }
}