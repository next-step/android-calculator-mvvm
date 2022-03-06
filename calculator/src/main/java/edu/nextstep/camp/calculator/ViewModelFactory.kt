package edu.nextstep.camp.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.data.di.RepositoryInjector

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        CalculatorViewModel::class.java -> createCalculatorViewModel()
        else -> throw IllegalArgumentException("can not find $modelClass")
    } as T

    private fun createCalculatorViewModel(): CalculatorViewModel {
        val repository = RepositoryInjector.provideCalculatorRepository(context)
        return CalculatorViewModel(repository = repository)
    }
}
