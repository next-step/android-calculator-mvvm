package edu.nextstep.camp.calculator.data.di

import android.content.Context
import edu.nextstep.camp.calculator.domain.CalculatorRepository

object RepoInjector {
    fun provideCalculatorRepository(context: Context): CalculatorRepository {
        return RepoInjectorHelper().provideCalculatorRepository(context)
    }
}