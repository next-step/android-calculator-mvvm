package edu.nextstep.camp.data

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.domain.calculator.CalculationRecord
import edu.nextstep.camp.domain.calculator.CalculationRecordsRepository
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultCalculationRecordsRepositoryTest {
    private lateinit var calculationRecordsDao: CalculationRecordsDao
    private lateinit var defaultCalculationRepository: CalculationRecordsRepository

    @Test
    fun `계산기록을 저장하면 room에 저장된다`() = runTest {
        //given
        val record = CalculationRecord(Expression(listOf("1", Operator.Plus, "2")), 3)
        val expected = CalculationRecordMapper.mapToData(record)

        calculationRecordsDao = mockk(relaxed = true)
        defaultCalculationRepository = DefaultCalculationRecordsRepository(calculationRecordsDao)

        //when
        defaultCalculationRepository.saveCalculationRecord(record)

        //then
        coVerify { calculationRecordsDao.insertCalculationRecords(expected) }
    }

    @Test
    fun `계산기록을 불러오면 room에 저장된 계산기록을 불러온다`() = runTest {
        //given
        val expected = CalculationRecord(Expression(listOf("1", Operator.Plus, "2")), 3)

        calculationRecordsDao = object : CalculationRecordsDao {
            override suspend fun insertCalculationRecords(vararg calculationRecordEntities: CalculationRecordEntity) {}
            override suspend fun loadCalculationRecords(): List<CalculationRecordEntity> = listOf(CalculationRecordMapper.mapToData(expected))
        }

        defaultCalculationRepository = DefaultCalculationRecordsRepository(calculationRecordsDao)

        //when
        val actual = defaultCalculationRepository.loadCalculationRecords()

        //then
        assertThat(actual.last()).isEqualTo(expected)
    }
}