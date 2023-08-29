/**
 * @author Daewon on 28,August,2023
 *
 */

package camp.nextstep.edu.calculator

import android.app.Application
import camp.nextstep.edu.calculator.data.DataInjector

class CalculatorApplication : Application() {
    val calculatorRepository by lazy { DataInjector.provideCalculatorRepository(this) }
}
