package edu.nextstep.camp.calculator.domain

import edu.nextstep.camp.calculator.domain.model.RecordStatement

class CalculatorRepository {
    private val recordStatementList = mutableListOf<RecordStatement>()

    fun saveStatement(recordStatement: RecordStatement) {
        recordStatementList.add(recordStatement)
    }

    fun getRecordStatement(): RecordStatement = recordStatementList.last()
}
