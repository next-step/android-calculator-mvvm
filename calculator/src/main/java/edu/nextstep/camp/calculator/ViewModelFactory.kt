package edu.nextstep.camp.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.domain.history.HistoryRepository

class ViewModelFactory(
    private val historyManager: HistoryRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            (CalculatorViewModel(historyManager)) as T
        } else {
            throw IllegalArgumentException("잘못된 타입입니다. ==> ${modelClass.simpleName}")
        }
    }
}