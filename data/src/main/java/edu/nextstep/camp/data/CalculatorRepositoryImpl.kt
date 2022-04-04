package edu.nextstep.camp.data

import edu.nextstep.camp.domain.CalculatorHistory
import edu.nextstep.camp.domain.CalculatorRepository

internal class CalculatorRepositoryImpl(private val calculatorDao: CalculatorDao): CalculatorRepository {

    override fun addHistory(calculatorHistory: CalculatorHistory) {
        calculatorDao.insertCalculatorHisotry(CalculatorHistoryEntity(calculatorHistory.expression,calculatorHistory.result))
    }

    override fun getHistory(): List<CalculatorHistory> {
       return calculatorDao.getCalculatorHisotry().map(CalculatorHistoryEntity::toDomain)
    }
}