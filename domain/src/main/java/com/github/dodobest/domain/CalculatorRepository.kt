package com.github.dodobest.domain

interface CalculatorRepository<T> {
    fun addMemory(resultRecord: T)
    fun getMemories(): List<T>
}