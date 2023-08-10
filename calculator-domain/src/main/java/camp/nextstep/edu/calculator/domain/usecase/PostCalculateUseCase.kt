package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.model.History
import camp.nextstep.edu.calculator.domain.repository.HistoryRepository

class PostCalculateUseCase(
    private val repository: HistoryRepository
) {
    suspend operator fun invoke(calculator: Calculator, expression: Expression): Result<Int> {
        val result = calculator.calculate(expression.toString())
        result?.let {
            repository.insertHistory(History(expressions = expression.toString(), result = result))
            return Result.success(result)
        }
        return Result.failure(Throwable(message = "완성되지 않은 수식입니다."))
    }
}