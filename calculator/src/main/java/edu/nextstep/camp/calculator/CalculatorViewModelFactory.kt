package edu.nextstep.camp.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.data.di.DAOInjector
import edu.nextstep.camp.calculator.data.di.DataBaseInjector
import edu.nextstep.camp.calculator.data.di.DataSourceInjector
import edu.nextstep.camp.calculator.data.di.RepositoryInjector
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression

class CalculatorViewModelFactory(
    private val context: Context,
    private val initialExpression: Expression = Expression.EMPTY,
    private val calculator: Calculator = Calculator()
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            return CalculatorViewModel(
                initialExpression = initialExpression,
                calculator = calculator,
                expressionHistoryRepository = RepositoryInjector.provideExpressionHistoryRepository(
                    expressionHistoryLocalDataSource = DataSourceInjector.provideExpressionHistoryLocalDataSource(
                        expressionHistoryDAO = DAOInjector.provideExpressionHistoryDAO(
                            calculatorDataBase = DataBaseInjector.provideCalculatorDataBase(context)
                        )
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}