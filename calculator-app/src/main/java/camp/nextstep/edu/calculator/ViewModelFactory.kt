package camp.nextstep.edu.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.domain.usecase.GetCalculatorResultUseCase
import camp.nextstep.edu.calculator.domain.usecase.SaveCalculatorResultUseCase
import camp.nextstep.edu.calculator.local.di.InjectRepositoryImpl.repositoryImpl
import java.util.concurrent.ExecutorService

class ViewModelFactory(
    private val context: Context,
    private val executorService: ExecutorService
    ) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> {
                createCalculatorViewModel()
            }
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun createCalculatorViewModel(): CalculatorViewModel {
        val repository = repositoryImpl(context, executorService)

        val getCalculatorResultUseCase = GetCalculatorResultUseCase(repository)
        val saveCalculatorResultUseCase = SaveCalculatorResultUseCase(repository)

        return CalculatorViewModel(
            getCalculatorResultUseCase,
            saveCalculatorResultUseCase
        )
    }
}