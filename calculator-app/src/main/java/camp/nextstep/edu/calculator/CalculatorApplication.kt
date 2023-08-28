/**
 * @author Daewon on 28,August,2023
 *
 */

package camp.nextstep.edu.calculator

import android.app.Application
import camp.nextstep.edu.calculator.data.local.CalculatorDatabase
import camp.nextstep.edu.calculator.data.repository.CalculatorRepositoryImpl

class CalculatorApplication : Application() {

    private val dataBase by lazy { CalculatorDatabase.getDatabase(this) }
    val repository by lazy { CalculatorRepositoryImpl(dataBase.calculatorDao()) }
}
