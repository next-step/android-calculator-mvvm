package edu.nextstep.camp.calculator.domain

interface CalculationResultRepository {
    suspend fun insert(vararg calculatedResult: CalculationResult)
    suspend fun getAll(): List<CalculationResult>
}