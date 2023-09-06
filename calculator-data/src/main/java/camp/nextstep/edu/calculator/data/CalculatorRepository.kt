package camp.nextstep.edu.calculator.data

import camp.nextstep.edu.calculator.data.datasource.local.dao.CalculationResultDao
import camp.nextstep.edu.calculator.data.datasource.local.entity.CalculationResultEntity
import camp.nextstep.edu.calculator.domain.CalculatorRepositoryInterface
import camp.nextstep.edu.calculator.domain.CalculationResult

class CalculatorRepository(
    private val calculationResultDao: CalculationResultDao
) : CalculatorRepositoryInterface {

    override suspend fun getCalculatorResults(): List<CalculationResult> {
        return calculationResultDao.selectAll().map {
            CalculationResultEntity.mapToDomainModel(it)
        }
    }

    override suspend fun insertCalculatorResult(calculationResult: CalculationResult) {
        calculationResultDao.insert(CalculationResultEntity.mapFromDomainModel(calculationResult))
    }
}