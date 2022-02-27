package com.github.dodobest.data

import android.content.Context

object Injector {
    fun provideCalculatorRepository(context: Context): CalculatorRepository {
        return CalculatorRepositoryImpl(AppDatabase.getInstance(context))
    }
}