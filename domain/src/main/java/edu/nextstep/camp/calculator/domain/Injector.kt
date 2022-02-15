package edu.nextstep.camp.calculator.domain

object Injector {
    fun providesExpression(): Expression = Expression.getInstance()
    fun providesCalculatorRepository(): CalculatorRepository = CalculatorRepository.getInstance()
}