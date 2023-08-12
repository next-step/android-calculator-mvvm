package camp.nextstep.edu.calculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.domain.repo.HistoryRepository

class ViewModelFactory(private val historyRepository: HistoryRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> createMainViewModel()
            else -> throw IllegalArgumentException("Cannot find {$modelClass}")
        } as T
    }

    private fun createMainViewModel(): CalculatorViewModel {
        return CalculatorViewModel(repository = historyRepository)
    }
}
