package edu.nextstep.camp.calculator.domain

interface CalculationResultDataBaseRepository {
    suspend fun insert(vararg calculatedResult: CalculationResult)
    suspend fun getAll(): List<CalculationResult>
}