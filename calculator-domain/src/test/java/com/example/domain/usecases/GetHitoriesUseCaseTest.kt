package com.example.domain.usecases

import com.example.domain.models.History
import com.example.domain.repositories.HistoryRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

class GetHitoriesUseCaseTest {

    private val historyRepository: HistoryRepository = mockk(relaxed = true)
    private val getHistoriesUseCase = GetHistoriesUseCase(historyRepository)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    @Test
    fun `저장된_기록이_없으면_빈_리스트를_반환한다`() = testScope.runTest {

        every { historyRepository.getHistories() } returns flow {
            emit(
                emptyList()
            )
        }

        // When
        val flow = getHistoriesUseCase()

        // Then
        assertTrue(
            flow.first() == emptyList<History>()
        )
    }


    @Test
    fun `저장된_기록이_있으면_기록들을__반환한다`() = testScope.runTest {
        // Given
        every { historyRepository.getHistories() } returns flow {
            emit(
                listOf(
                    History(
                        "1 + 1",
                        2
                    )
                )
            )
        }

        // When
        val flow = getHistoriesUseCase()

        // Then
        assertTrue(
            flow.first() == listOf(
                History(
                    "1 + 1",
                    2
                )
            )
        )
    }
}
