package camp.nextstep.edu.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.domain.repository.RecordRepository

class CalculatorViewModelFactory(
    private val recordRepository: RecordRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            CalculatorViewModel(recordRepository) as T
        } else {
            throw java.lang.IllegalArgumentException()
        }
    }
}
