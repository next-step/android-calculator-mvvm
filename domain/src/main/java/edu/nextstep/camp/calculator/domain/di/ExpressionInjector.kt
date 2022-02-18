package edu.nextstep.camp.calculator.domain.di

import edu.nextstep.camp.calculator.domain.Expression

object ExpressionInjector {
    fun providesExpression(): Expression = Expression.getInstance()
}
