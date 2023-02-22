package camp.nextstep.edu.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.data.Injector


class ViewModelFactory(
    private val applicationContext: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java ->
                provideCalculatorViewModel(applicationContext)
            else ->
                error("UnSupported ViewModel")
        } as T
    }

    private fun provideCalculatorViewModel(
        applicationContext: Context
    ): CalculatorViewModel {
        val (saveResultUseCase, getAllResultsResultUseCase) =
            Injector.provideDependenciesForCalculateViewModel(applicationContext)

        return CalculatorViewModel(
            saveResultUseCase,
            getAllResultsResultUseCase
        )
    }
}
