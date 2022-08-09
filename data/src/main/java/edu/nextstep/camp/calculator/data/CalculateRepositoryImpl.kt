package edu.nextstep.camp.calculator.data

import android.content.Context
import edu.nextstep.camp.calculator.data.model.CalculateResultEntity
import edu.nextstep.camp.calculator.domain.CalculateResult
import edu.nextstep.camp.calculator.domain.repository.CalculateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CalculateRepositoryImpl(context: Context): CalculateRepository {
    private val calculateDatabase: CalculatorDatabase = CalculatorDatabase.getDatabase(context)

    override suspend fun insertCalculateResult(calculateResult: CalculateResult) {
        calculateDatabase.calculateResultDao().insertCalculateResult(CalculateResultEntity(
            expression = calculateResult.expression.toString(),
            result = calculateResult.result,
        ))
    }

    override suspend fun getCalculateResults(): Flow<List<CalculateResult>?> {
        return calculateDatabase.calculateResultDao().getCalculateResults().map {
            it?.map { calculateResultEntity ->
                calculateResultEntity.toCalculateResult()
            }
        }
    }
}
