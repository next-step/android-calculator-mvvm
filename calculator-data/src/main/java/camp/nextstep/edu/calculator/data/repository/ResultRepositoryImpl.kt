package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.db.CalculatorDatabase
import camp.nextstep.edu.calculator.data.db.entity.ResultEntity
import camp.nextstep.edu.calculator.data.toDto
import camp.nextstep.edu.calculator.data.toEntity
import camp.nextstep.edu.calculator.domain.model.CalculatorResult
import camp.nextstep.edu.calculator.domain.repository.ResultRepository


internal class ResultRepositoryImpl(
    private val db: CalculatorDatabase
) : ResultRepository {

    override fun saveResult(result: CalculatorResult) {
        db.resultRecordsDao().saveResult(result.toEntity())
    }

    override fun getAllResults() =
        db.resultRecordsDao().getAllResultRecords().map(ResultEntity::toDto)
}
