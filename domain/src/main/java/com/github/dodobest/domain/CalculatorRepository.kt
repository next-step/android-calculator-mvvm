package com.github.dodobest.domain

interface CalculatorRepository {
    fun addMemory(expression: String, result: String)
    fun getMemories(): List<ResultRecord>
}