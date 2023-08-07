package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.model.History
import camp.nextstep.edu.calculator.domain.repository.HistoryRepository

class GetHistoriesUseCase(
    private val repository: HistoryRepository
) {
    suspend operator fun invoke(): List<History> {
        return repository.getHistories()
    }
}