package edu.nextstep.camp.calculator.data.di

import android.content.Context
import edu.nextstep.camp.calculator.data.CalculatorDatabase
import edu.nextstep.camp.calculator.data.EvaluationRecordDao

object AppModule {
    fun provideCalculatorDatabase(context: Context): CalculatorDatabase {
        return CalculatorDatabase.getInstance(context)
    }

    fun provideEvaluationRecordDao(calculatorDatabase: CalculatorDatabase): EvaluationRecordDao {
        return calculatorDatabase.evaluationRecordDao()
    }
}
