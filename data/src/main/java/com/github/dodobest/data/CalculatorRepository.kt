package com.github.dodobest.data

interface CalculatorRepository {
    fun addMemory(resultRecord: ResultRecord)
    fun getMemories(): List<ResultRecord>
}