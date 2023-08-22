package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.data.ResultExpression
import camp.nextstep.edu.calculator.domain.repository.ResultExpressionRepository

class AddResultExpressionUseCase(
    private val resultExpressionRepository: ResultExpressionRepository
) {
    suspend operator fun invoke(resultExpression: ResultExpression) {
        resultExpressionRepository.addResultExpression(resultExpression)
    }
}
