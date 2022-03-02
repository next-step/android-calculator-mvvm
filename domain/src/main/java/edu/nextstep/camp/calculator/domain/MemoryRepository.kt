package edu.nextstep.camp.calculator.domain

import kotlinx.coroutines.flow.Flow

interface MemoryRepository {
    fun getAllMemory(): Flow<List<Memory>>

    suspend fun addMemory(memory: Memory)
}
