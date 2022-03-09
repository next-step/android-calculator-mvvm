package com.github.dodobest.data.util

import android.content.Context
import com.github.dodobest.data.AppDatabase
import com.github.dodobest.data.CalculatorRepositoryImpl
import com.github.dodobest.domain.CalculatorRepository

object Injector {
    fun provideCalculatorRepository(context: Context): CalculatorRepository {
        return CalculatorRepositoryImpl(AppDatabase.getInstance(context))
    }
}