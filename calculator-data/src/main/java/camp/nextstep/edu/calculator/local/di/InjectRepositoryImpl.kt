package camp.nextstep.edu.calculator.local.di

import android.content.Context
import camp.nextstep.edu.calculator.domain.repository.CalculatorResultRepository
import camp.nextstep.edu.calculator.local.di.InjectDatabase.getDB
import camp.nextstep.edu.calculator.local.impl.CalculatorResultRepositoryImpl

object InjectRepositoryImpl {
    fun repositoryImpl(context: Context): CalculatorResultRepository {
        val dao = getDB(context)?.calculatorResultDao() ?: throw IllegalArgumentException()
        return CalculatorResultRepositoryImpl(dao)
    }
}