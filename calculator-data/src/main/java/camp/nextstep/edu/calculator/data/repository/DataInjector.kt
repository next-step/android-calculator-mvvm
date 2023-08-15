package camp.nextstep.edu.calculator.data.repository

import android.content.Context
import camp.nextstep.edu.calculator.domain.repository.MemoryRepository

object DataInjector {
    fun provideMemoryRepository(context: Context): MemoryRepository {
        return MemoryLocalRepository(context)
    }
}
