package edu.nextstep.camp.domain

interface CalculatorRepository {
    suspend fun getAll(): List<Calculation>

    suspend fun insert(calculation: Calculation)
}