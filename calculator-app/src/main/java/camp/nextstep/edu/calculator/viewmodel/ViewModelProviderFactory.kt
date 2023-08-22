package camp.nextstep.edu.calculator.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.CalculatorViewModel
import camp.nextstep.edu.calculator.data.repository.DataInjector
import camp.nextstep.edu.calculator.domain.usecase.AddResultExpressionUseCase
import camp.nextstep.edu.calculator.domain.usecase.CalculateUseCase
import camp.nextstep.edu.calculator.domain.usecase.GetResultExpressionListUseCase

@Suppress("UNCHECKED_CAST")
object ViewModelProviderFactory {

    fun getCalculatorViewModel(context: Context): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
                    val repository = DataInjector.provideResultExpressionRepository(context)
                    CalculatorViewModel(
                        calculateUseCase = CalculateUseCase(),
                        addResultExpressionUseCase = AddResultExpressionUseCase(repository),
                        getResultExpressionListUseCase = GetResultExpressionListUseCase(repository)
                    ) as T
                } else {
                    throw IllegalArgumentException()
                }
            }
        }
    }
}
