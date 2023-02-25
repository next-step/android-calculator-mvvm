package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.model.CalculatorResult
import camp.nextstep.edu.calculator.domain.repository.ResultRepository
import kotlinx.coroutines.flow.Flow


class GetAllResultsUseCase(
    private val repo: ResultRepository
) {

    operator fun invoke(): Flow<List<CalculatorResult>> = repo.getAllResults()
}
