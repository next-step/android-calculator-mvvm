package edu.nextstep.camp.calculator.data

import android.content.Context
import edu.nextstep.camp.calculator.domain.repository.CalculateRepository

object Injector {
    fun provideCalculateRepository(context: Context): CalculateRepository {
        return CalculateRepositoryImpl(context)
    }
}
