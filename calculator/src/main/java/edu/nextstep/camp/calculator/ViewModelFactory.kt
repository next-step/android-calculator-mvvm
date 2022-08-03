package edu.nextstep.camp.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nextstep.camp.calculator.data.RepositoryInjector

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> createCalculatorViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun createCalculatorViewModel() = CalculatorViewModel(
        recordsRepository = RepositoryInjector.provideRecordsRepository(context)
    )
}
