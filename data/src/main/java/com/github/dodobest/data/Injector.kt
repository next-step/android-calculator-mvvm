package com.github.dodobest.data

import android.content.Context
import com.github.dodobest.domain.CalculatorRepository

object Injector {
    fun provideCalculatorRepository(context: Context): CalculatorRepository {
        return CalculatorRepositoryImpl(AppDatabase.getInstance(context))
    }
}