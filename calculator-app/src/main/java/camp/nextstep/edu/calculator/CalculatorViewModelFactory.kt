/**
 * @author Daewon on 29,August,2023
 *
 */

package camp.nextstep.edu.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository


class CalculatorViewModelFactory(private val calculatorRepository: CalculatorRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CalculatorViewModel(calculatorRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
