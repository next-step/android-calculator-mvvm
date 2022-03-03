package edu.nextstep.camp.calculator.domain

import kotlinx.coroutines.flow.Flow

interface MemoryRepository {
    fun getMemories(): Flow<Memories>

    suspend fun addMemory(memory: Memory)
}
