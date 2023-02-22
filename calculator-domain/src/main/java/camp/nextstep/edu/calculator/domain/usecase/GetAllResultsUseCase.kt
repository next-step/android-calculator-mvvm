package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.repository.ResultRepository


class GetAllResultsUseCase(
    private val repo: ResultRepository
) {

    operator fun invoke() = repo.getAllResults()
}
