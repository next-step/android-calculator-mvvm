package edu.nextstep.camp.calculator.domain

import edu.nextstep.camp.calculator.domain.model.RecordStatement
import kotlinx.coroutines.flow.Flow

interface CalculatorRepository {

    suspend fun saveStatement(recordStatement: RecordStatement)

    fun getStatements(): Flow<List<RecordStatement>>
}
