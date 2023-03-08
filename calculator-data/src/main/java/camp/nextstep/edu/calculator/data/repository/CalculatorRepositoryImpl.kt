package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.room.dao.CalculatorDao
import camp.nextstep.edu.calculator.data.room.entity.RecordEntity
import camp.nextstep.edu.calculator.domain.model.Record
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CalculatorRepositoryImpl(
    private val dao: CalculatorDao
) : CalculatorRepository {

    override fun insertRecord(record: Record) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertRecord(RecordEntity.from(record))
        }
    }

    override fun getAllRecords(): Flow<List<Record>> {
        return dao.getAllRecords().map { recordEntities ->
            recordEntities.map {
                it.toDomain()
            }
        }
    }
}