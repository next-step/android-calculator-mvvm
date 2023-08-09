package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.model.History
import camp.nextstep.edu.calculator.domain.repository.HistoryRepository

class PostCalculateUseCase(
    private val repository: HistoryRepository
) {
    operator fun invoke(history: History) {
        return repository.insertHistory(history)
    }
}