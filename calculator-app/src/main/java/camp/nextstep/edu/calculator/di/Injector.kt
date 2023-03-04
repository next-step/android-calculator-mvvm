package camp.nextstep.edu.calculator.di

import android.content.Context
import camp.nextstep.edu.calculator.CalculatorViewModel
import camp.nextstep.edu.calculator.data.di.DataInjector
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository

object Injector {
    fun provideCalculatorViewModel(
        context: Context,
        calculatorRepository: CalculatorRepository = DataInjector.provideCalculatorRepository(context)
    ): CalculatorViewModel {
        return CalculatorViewModel(
            calculatorRepository = calculatorRepository
        )
    }
}