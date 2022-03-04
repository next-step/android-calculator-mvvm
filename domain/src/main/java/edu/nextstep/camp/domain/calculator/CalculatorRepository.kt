package edu.nextstep.camp.domain.calculator

import edu.nextstep.camp.domain.calculator.model.CalculatorRecord

interface CalculatorRepository {
    fun getAllRecord(): kotlinx.coroutines.flow.Flow<List<CalculatorRecord>>
}
