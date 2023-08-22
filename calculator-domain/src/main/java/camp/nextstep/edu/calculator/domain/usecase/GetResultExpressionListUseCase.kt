package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.data.ResultExpression
import camp.nextstep.edu.calculator.domain.repository.ResultExpressionRepository

class GetResultExpressionListUseCase(
    private val resultExpressionRepository: ResultExpressionRepository
) {
    suspend operator fun invoke(): List<ResultExpression> {
        return resultExpressionRepository.getResultExpressionList()
    }
}
