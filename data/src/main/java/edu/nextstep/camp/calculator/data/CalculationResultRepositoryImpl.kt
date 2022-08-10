package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.CalculationResult
import edu.nextstep.camp.calculator.domain.CalculationResultRepository

internal class CalculationResultRepositoryImpl(private val calculationResultLocalDatabase: CalculationResultDatabase) :
    CalculationResultRepository {
    override suspend fun insert(vararg calculatedResult: CalculationResult) {
        val entriesArray = calculatedResult.map(CalculationResultEntity::calculationResultToEntity)
            .toTypedArray()
        calculationResultLocalDatabase.calculationResultDao()
            .insert(*entriesArray)
    }

    override suspend fun getAll(): List<CalculationResult> {
        return calculationResultLocalDatabase.calculationResultDao()
            .getAll()
            .map(CalculationResultEntity::toCalculationResult)
    }
}