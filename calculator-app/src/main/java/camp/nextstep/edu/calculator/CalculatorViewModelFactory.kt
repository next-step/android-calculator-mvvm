package camp.nextstep.edu.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.di.AppModule
import java.util.concurrent.ExecutorService

class CalculatorViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> AppModule.provideCalculatorViewModel(context = context)
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }
}