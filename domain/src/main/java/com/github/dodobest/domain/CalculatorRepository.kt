package com.github.dodobest.domain

interface CalculatorRepository {
    fun addMemory(resultRecord: ResultRecord)
    fun getMemories(): List<ResultRecord>
}