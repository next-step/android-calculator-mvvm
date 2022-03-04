package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.CalculatorRepository
import com.github.dodobest.domain.ResultRecord

class AddMemoryUseCase(private val repository: CalculatorRepository) {
    fun invoke(resultRecord: ResultRecord) {
        repository.addMemory(resultRecord)
    }
}