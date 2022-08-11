package edu.nextstep.camp.calculator.data.di

import android.content.Context
import androidx.room.Room
import edu.nextstep.camp.calculator.data.CalculatorDataBase

object DataBaseInjector {
    fun provideCalculatorDataBase(context: Context): CalculatorDataBase =
        Room.databaseBuilder(
            context,
            CalculatorDataBase::class.java, "calculator-database"
        ).build()
}