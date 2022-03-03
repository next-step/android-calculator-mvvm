package edu.nextstep.camp.domain

interface CalculatorRepository {
    fun getAll(): List<Calculation>

    fun insert(calculation: Calculation)
}