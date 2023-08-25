package com.example.calculator.data

import camp.nextstep.edu.calculator.domain.CalculatorRepository
import camp.nextstep.edu.calculator.domain.Memory

// DB인스턴스 생성

internal class CalculatorRepositoryImpl(private val calculatorDao: CalculatorDao) : CalculatorRepository {

    override fun insertMemory(memory: Memory) {
        calculatorDao.insertMemory(
            MemoryEntity(
                expression = memory.expression,
                result = memory.result
            )
        )
    }

    override fun getMemories(): List<Memory> {
        val arrMemoryList = arrayListOf<Memory>()
        for(memoryEntity in calculatorDao.getMemories()) {
            arrMemoryList.add(Memory(index = memoryEntity.id, expression = memoryEntity.expression, result = memoryEntity.result))
        }
        return arrMemoryList.toList()
    }


}
