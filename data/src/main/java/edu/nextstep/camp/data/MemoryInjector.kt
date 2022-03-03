package edu.nextstep.camp.data

import android.content.Context
import edu.nextstep.camp.domain.CalculatorRepository

object Injector {
    fun provideMemoryRepository(context: Context): CalculatorRepository {
        return MemoryRepositoryImpl(AppDataBase.getInstance(context).memoryDao())
    }
}