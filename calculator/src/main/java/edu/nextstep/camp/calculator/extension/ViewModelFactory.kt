package edu.nextstep.camp.calculator.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.CalculatorViewModel
import edu.nextstep.camp.calculator.data.historyStorage.HistoryDatabase
import edu.nextstep.camp.calculator.data.historyStorage.HistoryManager
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val historyManager: HistoryManager
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            (CalculatorViewModel(historyManager)) as T
        } else {
            throw IllegalArgumentException("잘못된 타입입니다.")
        }
    }
}