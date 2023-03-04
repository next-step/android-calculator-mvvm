package camp.nextstep.edu.calculator.domain.repository

import camp.nextstep.edu.calculator.domain.model.Record
import kotlinx.coroutines.flow.Flow

interface CalculatorRepository {
    fun getAllRecords(): Flow<List<Record>>
}