package camp.nextstep.edu.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.data.repository.Injector
import camp.nextstep.edu.calculator.domain.repository.RecordRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CalculatorViewModelFactory(
    context: Context
) : ViewModelProvider.Factory {
    private val recordRepository: RecordRepository = Injector.provideRecordRepository(context)
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            CalculatorViewModel(recordRepository, dispatcher) as T
        } else {
            throw java.lang.IllegalArgumentException()
        }
    }
}
