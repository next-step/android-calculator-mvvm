package camp.nextstep.edu.calculator

import android.content.Context
import camp.nextstep.edu.calculator.data.di.DataModule
import camp.nextstep.edu.calculator.domain.ResultRepository

class CalculatorAppContainer(context: Context) {
    val resultRepository: ResultRepository = DataModule.provideResultRepository(context)
}
