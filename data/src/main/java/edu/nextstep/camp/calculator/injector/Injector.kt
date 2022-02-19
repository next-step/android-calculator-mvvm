package edu.nextstep.camp.calculator.injector

import android.content.Context
import edu.nextstep.camp.calculator.MemoryDatabase
import edu.nextstep.camp.calculator.repository.MemoryRepository
import edu.nextstep.camp.calculator.repository.MemoryRepositoryImpl

object Injector {
    fun provideMemoryRepository(context: Context): MemoryRepository {
        return MemoryRepositoryImpl(
            memoryDao = MemoryDatabase.getInstance(context).getMemoryDao()
        )
    }
}