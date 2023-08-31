package com.example.calculator.data

import camp.nextstep.edu.calculator.domain.CalculatorRepository

object Injector {
    fun providesCalculatorRepository(calculatorDao: CalculatorDao): CalculatorRepository {
        return CalculatorRepositoryImpl(calculatorDao)
    }
}

