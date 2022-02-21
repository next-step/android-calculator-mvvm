package edu.nextstep.camp.calculator.domain

import edu.nextstep.camp.calculator.domain.model.Memories
import edu.nextstep.camp.calculator.domain.model.Memory

interface CalculatorRepository {

    fun addMemory(memory: Memory)

    fun getMemories(): Memories
}