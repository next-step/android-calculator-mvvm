package camp.nextstep.edu.calculator.data.di

import android.content.Context
import androidx.room.Room
import camp.nextstep.edu.calculator.data.repository.CalculatorRepositoryImpl
import camp.nextstep.edu.calculator.data.room.CalculatorDatabase
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository

object DataInjector {
    fun provideCalculatorDatabase(context: Context): CalculatorDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CalculatorDatabase::class.java,
            "calculator.db"
        ).build()
    }

    fun provideCalculatorRepository(
        context: Context,
        calculatorDatabase: CalculatorDatabase = provideCalculatorDatabase(context)
    ): CalculatorRepository {
        return CalculatorRepositoryImpl(
            dao = calculatorDatabase.calculatorDao()
        )
    }
}