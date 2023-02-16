package camp.nextstep.edu.calculator.local.di

import android.content.Context
import camp.nextstep.edu.calculator.domain.repository.CalculatorResultRepository
import camp.nextstep.edu.calculator.local.di.InjectDatabase.getDB
import camp.nextstep.edu.calculator.local.impl.CalculatorResultRepositoryImpl
import java.util.concurrent.ExecutorService

object InjectRepositoryImpl {
    fun repositoryImpl(
        context: Context,
        executorService: ExecutorService
    ): CalculatorResultRepository {
        val dao = getDB(context)?.calculatorResultDao() ?: throw IllegalArgumentException()
        return CalculatorResultRepositoryImpl(dao, executorService)
    }
}