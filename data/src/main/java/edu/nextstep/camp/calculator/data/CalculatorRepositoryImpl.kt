package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.data.local.CalculatorDao
import edu.nextstep.camp.calculator.data.mapper.map
import edu.nextstep.camp.calculator.data.model.Statement
import edu.nextstep.camp.calculator.domain.CalculatorRepository
import edu.nextstep.camp.calculator.domain.model.RecordStatement
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class CalculatorRepositoryImpl private constructor(
    private val calculatorDao: CalculatorDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CalculatorRepository {

    override suspend fun saveStatement(recordStatement: RecordStatement) =
        withContext(ioDispatcher) {
            val (uuid, expression, calculateResult) = recordStatement
            calculatorDao.insertStatement(Statement(uuid, expression, calculateResult))
        }

    override fun getStatements(): Flow<List<RecordStatement>> =
        calculatorDao.getAll()
            .map { stateList -> stateList.map() }
            .flowOn(Dispatchers.IO)

    companion object {
        @Volatile
        private var instance: CalculatorRepositoryImpl? = null
        fun getInstance(calculatorDao: CalculatorDao): CalculatorRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: CalculatorRepositoryImpl(calculatorDao).also { instance = it }
            }
    }
}
