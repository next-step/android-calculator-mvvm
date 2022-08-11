package edu.nextstep.camp.calculator.data.di

import edu.nextstep.camp.calculator.data.CalculatorDataBase
import edu.nextstep.camp.calculator.data.service.ExpressionHistoryDAO

object DAOInjector {

    fun provideExpressionHistoryDAO(calculatorDataBase: CalculatorDataBase): ExpressionHistoryDAO =
        calculatorDataBase.expressionHistoryDao()
}