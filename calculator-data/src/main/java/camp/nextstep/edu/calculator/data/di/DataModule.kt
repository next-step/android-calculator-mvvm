package camp.nextstep.edu.calculator.data.di

import android.content.Context
import camp.nextstep.edu.calculator.data.CalculatorDatabase
import camp.nextstep.edu.calculator.data.repository.ResultRepositoryImpl
import camp.nextstep.edu.calculator.domain.ResultRepository

object DataModule {
    private fun provideResultDao(context: Context) =
        CalculatorDatabase.getInstance(context).resultDao()

    fun provideResultRepository(context: Context): ResultRepository =
        ResultRepositoryImpl(provideResultDao(context))
}
