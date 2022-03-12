package edu.nextstep.camp.calculator.domain

import edu.nextstep.camp.calculator.domain.model.Memory

interface HistoryRepository {
    suspend fun getAll(): List<Memory>
    suspend fun insert(memory: Memory)
}