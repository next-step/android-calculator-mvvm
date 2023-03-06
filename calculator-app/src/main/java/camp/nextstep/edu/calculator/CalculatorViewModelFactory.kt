package camp.nextstep.edu.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.data.repository.Injector
import camp.nextstep.edu.calculator.domain.repository.RecordRepository

class CalculatorViewModelFactory(
    context: Context
) : ViewModelProvider.Factory {
    private val recordRepository: RecordRepository = Injector.provideRecordRepository(context)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            CalculatorViewModel(recordRepository) as T
        } else {
            throw java.lang.IllegalArgumentException()
        }
    }
}
