package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.dao.ResultDao
import camp.nextstep.edu.calculator.data.entity.ResultEntity
import camp.nextstep.edu.calculator.domain.Result
import camp.nextstep.edu.calculator.domain.ResultRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ResultRepositoryImpl(private val resultDao: ResultDao) : ResultRepository {

    override val resultList: Flow<List<Result>>
        get() = resultDao.getAllResult()
            .map { resultEntityList ->
                resultEntityList.map { resultEntity -> resultEntity.mapToDomain() }
            }

    override fun getResults(): Flow<List<Result>> = resultDao.getAllResult()
            .map { resultEntityList ->
                resultEntityList.map { resultEntity -> resultEntity.mapToDomain() }
            }


    override suspend fun putResult(expression: String, result: Int) {
        resultDao.insertResult(
            ResultEntity(expression = expression, result = result)
        )
    }
}
