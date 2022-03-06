package edu.nextstep.camp.data

import edu.nextstep.camp.data.db.CalculatorRecordDao
import edu.nextstep.camp.data.model.CalculatorRecordEntity
import edu.nextstep.camp.data.model.toListOfDomain
import edu.nextstep.camp.domain.calculator.CalculatorRepository
import edu.nextstep.camp.domain.calculator.model.CalculatorRecord
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalCalculatorRepository private constructor(
    private val calculatorRecordDao: CalculatorRecordDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CalculatorRepository {
    override fun getAllRecord(): Flow<List<CalculatorRecord>> = calculatorRecordDao.getAllRecord()
        .map { records -> records.toListOfDomain() }
        .flowOn(ioDispatcher)

    override suspend fun addRecord(record: CalculatorRecord) = withContext(ioDispatcher) {
        val recordEntity = record.let {
            CalculatorRecordEntity(expression = it.expression, result = it.result)
        }
        calculatorRecordDao.insertRecord(recordEntity)
    }

    companion object {
        @Volatile
        private var instance: LocalCalculatorRepository? = null

        fun getInstance(calculatorRecordDao: CalculatorRecordDao): LocalCalculatorRepository =
            instance ?: synchronized(this) {
                instance ?: LocalCalculatorRepository(calculatorRecordDao).also { instance = it }
            }
    }
}
