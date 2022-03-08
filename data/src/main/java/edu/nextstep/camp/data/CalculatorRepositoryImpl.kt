package edu.nextstep.camp.data

import edu.nextstep.camp.domain.CalculatorHistoryData
import edu.nextstep.camp.domain.CalculatorRepository

internal class CalculatorRepositoryImpl(private val calculatorDao: CalculatorDao): CalculatorRepository {

    override fun addHistory(calculatorHistoryData: CalculatorHistoryData) {
        calculatorDao.insertCalculatorHisotry(CalculatorHistoryEntity(calculatorHistoryData.expression,calculatorHistoryData.result))
    }

    override fun getHistory(): List<CalculatorHistoryData> {
       return calculatorDao.getCalculatorHisotry().map(CalculatorHistoryEntity::toDomain)
    }
}