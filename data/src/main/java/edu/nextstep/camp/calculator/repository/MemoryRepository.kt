package edu.nextstep.camp.calculator.repository

import edu.nextstep.camp.calculator.Memory
import kotlinx.coroutines.flow.Flow

interface MemoryRepository {
    fun getAllMemory(): Flow<List<Memory>>

    suspend fun insert(memory: Memory)
}