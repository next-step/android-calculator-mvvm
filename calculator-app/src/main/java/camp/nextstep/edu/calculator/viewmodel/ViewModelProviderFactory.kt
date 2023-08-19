package camp.nextstep.edu.calculator.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.CalculatorViewModel
import camp.nextstep.edu.calculator.data.repository.DataInjector

object ViewModelProviderFactory {

    fun getCalculatorViewModel(context: Context): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
                    CalculatorViewModel(
                        resultExpressionRepository = DataInjector.provideResultExpressionRepository(context)
                    ) as T
                } else {
                    throw IllegalArgumentException()
                }
            }
        }
    }
}
