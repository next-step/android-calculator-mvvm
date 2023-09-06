package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Room
import camp.nextstep.edu.calculator.data.datasource.local.CalculatorDatabase
import camp.nextstep.edu.calculator.domain.CalculatorRepositoryInterface

object CalculatorModule {

    fun provideCalculatorRepository(context: Context): CalculatorRepositoryInterface {
        return CalculatorRepository(provideDatabase(context).calculatorResultDao())
    }

    private fun provideDatabase(context: Context): CalculatorDatabase {
        return Room.databaseBuilder(
            context,
            CalculatorDatabase::class.java,
        "calculator-db"
        ).build()
    }
}