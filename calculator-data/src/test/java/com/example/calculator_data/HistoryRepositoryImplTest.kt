package com.example.calculator_data

import androidx.test.core.app.ApplicationProvider
import com.example.domain.models.History
import com.example.domain.repositories.HistoryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HistoryRepositoryImplTest {
    private val historyRepository: HistoryRepository =
        RepositoryModule.providerHistoryRepository(
            DaoModule.provideHistoryDao(
                DatabaseModule.provideDatabase(
                    context = ApplicationProvider.getApplicationContext()
                )
            )
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    @Test
    fun `아무것도_없을_때_History_를_호출하면_빈_배열을_반환한다`() = testScope.runTest {
        Assert.assertTrue(historyRepository.getHistories().first().isEmpty())
    }

    @Test
    fun `History를_삽입할_수_있다`() = testScope.runTest {
        val flow = historyRepository.getHistories()
        Assert.assertTrue(flow.first().isEmpty())
        historyRepository.saveHistory(History(statement = "1 + 1", result = 2))

        Assert.assertTrue(flow.first().isNotEmpty())
        Assert.assertTrue(flow.first().size == 1)
    }
}
