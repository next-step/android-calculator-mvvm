package edu.nextstep.camp.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.data.CalculatorRecordDAO

class CalculatorViewModelFactory(calculatorRecordDAO: CalculatorRecordDAO) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            CalculatorViewModel() as T
        } else {
            throw IllegalArgumentException("can not find $modelClass")
        }
    }
}
