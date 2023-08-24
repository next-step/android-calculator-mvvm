package camp.nextstep.edu.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.data.CalculatorDao

class CalculatorViewModelFactory(private val initFormula: List<Any> = emptyList(), private val calculatorDao: CalculatorDao) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            return CalculatorViewModel(initFormula, calculatorDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
