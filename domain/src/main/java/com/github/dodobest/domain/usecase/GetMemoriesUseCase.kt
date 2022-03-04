package com.github.dodobest.domain.usecase

import com.github.dodobest.domain.CalculatorRepository
import com.github.dodobest.domain.ResultRecord

class GetMemoriesUseCase(private val repository: CalculatorRepository) {
    fun invoke() : List<ResultRecord> {
        return repository.getMemories()
    }
}