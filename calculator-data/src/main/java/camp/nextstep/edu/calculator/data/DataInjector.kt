package camp.nextstep.edu.calculator.data

import android.content.Context
import camp.nextstep.edu.calculator.data.local.CalculatorDatabase
import camp.nextstep.edu.calculator.data.repository.DefaultCalculatorRepository
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository

object DataInjector {
    fun provideCalculatorRepository(context: Context): CalculatorRepository {
        return DefaultCalculatorRepository(CalculatorDatabase.getDatabase(context).calculatorDao())
    }
}
