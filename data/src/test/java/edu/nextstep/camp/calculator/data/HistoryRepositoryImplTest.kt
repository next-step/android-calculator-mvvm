package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class HistoryRepositoryImplTest {

    private lateinit var historyDao: CalculationHistoryDao
    private lateinit var historyRepository: HistoryRepository

    @BeforeEach
    fun setUp() {
        historyDao = mockk(relaxed = true)
        historyRepository = HistoryRepositoryImpl(historyDao)
    }

    @Test
    fun `모든 계산 기록을 불러온다`() = runBlocking {
        // when
        historyRepository.getAllHistories()

        // then
        coVerify { historyDao.getCalculationHistories() }
    }

    @Test
    fun `계산 기록을 추가할 수 있다`() = runBlocking {
        // given
        val history = CalculationHistoryEntity(expression = Expression(listOf(5, Operator.Plus, 5)), result = "10")

        // when
        historyRepository.addHistory(history)

        // then
        coVerify { historyDao.insertCalculationHistory(history) }
    }

}