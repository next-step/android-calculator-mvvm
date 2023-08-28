/**
 * @author Daewon on 28,August,2023
 *
 */

package camp.nextstep.edu.calculator

import android.app.Application
import camp.nextstep.edu.calculator.data.local.CalculatorDatabase
import camp.nextstep.edu.calculator.data.repository.DefaultCalculatorRepository

class CalculatorApplication : Application() {

    private val dataBase by lazy { CalculatorDatabase.getDatabase(this) }
    val calculatorRepository by lazy { DefaultCalculatorRepository(dataBase.calculatorDao()) }
}
