package camp.nextstep.edu.calculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> createMainViewModel()
            else -> throw IllegalArgumentException("Cannot find model class")
        } as T
    }

    private fun createMainViewModel(): CalculatorViewModel {
        return CalculatorViewModel()
    }
}
