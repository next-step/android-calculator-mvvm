package camp.nextstep.edu.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.domain.usecase.CalculatorResultUseCase
import camp.nextstep.edu.calculator.local.di.InjectDatabase
import camp.nextstep.edu.calculator.local.di.InjectRepositoryImpl.repositoryImpl

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

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
        val repository = repositoryImpl(context)
        val useCase = CalculatorResultUseCase(repository)
        return CalculatorViewModel(useCase)
    }
}