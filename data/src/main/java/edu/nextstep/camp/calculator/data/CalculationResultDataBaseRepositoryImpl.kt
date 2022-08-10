package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.CalculationResult
import edu.nextstep.camp.calculator.domain.CalculationResultDataBaseRepository

internal class CalculationResultDataBaseRepositoryImpl(private val calculationResultDatabase: CalculationResultDatabase) :
    CalculationResultDataBaseRepository {
    override suspend fun insert(vararg calculatedResult: CalculationResult) {
        val entriesArray = calculatedResult.map(CalculationResultEntity::calculationResultToEntity)
            .toTypedArray()
        calculationResultDatabase.calculationResultDao()
            .insert(*entriesArray)
    }

    override suspend fun getAll(): List<CalculationResult> {
        return calculationResultDatabase.calculationResultDao()
            .getAll()
            .map(CalculationResultEntity::toCalculationResult)
    }
}