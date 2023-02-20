package camp.nextstep.edu.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.data.Injector


class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java ->
                provideCalculatorViewModel()
            else ->
                error("UnSupported ViewModel")
        } as T
    }

    private fun provideCalculatorViewModel(): CalculatorViewModel {
        val (saveResultUseCase, getAllResultsResultUseCase) =
            Injector.provideDependenciesForCalculateViewModel()

        return CalculatorViewModel(
            saveResultUseCase,
            getAllResultsResultUseCase
        )
    }
}
