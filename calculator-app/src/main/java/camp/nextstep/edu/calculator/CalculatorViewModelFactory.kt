package camp.nextstep.edu.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.domain.usecase.DeleteHistoryUseCase
import camp.nextstep.edu.calculator.domain.usecase.GetHistoriesUseCase
import camp.nextstep.edu.calculator.domain.usecase.InsertHistoryUseCase

class CalculatorViewModelFactory(
    private val getHistoriesUseCase: GetHistoriesUseCase,
    private val insertHistoryUseCase: InsertHistoryUseCase,
    private val deleteHistoryUseCase: DeleteHistoryUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> CalculatorViewModel(
                getHistoriesUseCase = getHistoriesUseCase,
                insertHistoryUseCase = insertHistoryUseCase,
                deleteHistoryUseCase = deleteHistoryUseCase
            )
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }
}