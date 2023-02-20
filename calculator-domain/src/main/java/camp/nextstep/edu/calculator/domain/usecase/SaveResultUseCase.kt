package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.model.CalculatorResult
import camp.nextstep.edu.calculator.domain.repository.ResultRepository


class SaveResultUseCase(
    private val repo: ResultRepository
) {

    operator fun invoke(
        expression: Expression,
        result: Int
    ) {
        repo.saveResult(
            CalculatorResult(expression.toString(), result.toString())
        )
    }
}
