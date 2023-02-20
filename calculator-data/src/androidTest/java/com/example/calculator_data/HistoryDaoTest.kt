package com.example.calculator_data

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.calculator_data.database.HistoryEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HistoryDaoTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    private val historyDao = DaoModule.provideHistoryDao(
        DatabaseModule.providerInMemoryDatabase(
            ApplicationProvider.getApplicationContext()
        )
    )

    @Test
    fun `아무것도_없을_때_HistoryEntity_를_호출하면_빈_배열을_반환한다`() = testScope.runTest {
        assertTrue(historyDao.getAll().first().isEmpty())
    }

    @Test
    fun `HistoryEntity를_삽입할_수_있다`() = testScope.runTest {
        val flow = historyDao.getAll()
        assertTrue(flow.first().isEmpty())
        historyDao.insert(HistoryEntity(statement = "1 + 1", result = 2))

        assertTrue(flow.first().isNotEmpty())
        assertTrue(flow.first().size == 1)
        assertTrue(flow.first()[0].uid == 1)
    }

    @Test
    fun `같은_값을_넣어도_UID를_통해_구분되어_저장된다`() = testScope.runTest {
        val flow = historyDao.getAll()
        assertTrue(flow.first().isEmpty())
        historyDao.insert(HistoryEntity(statement = "1 + 1", result = 2))
        historyDao.insert(HistoryEntity(statement = "1 + 1", result = 2))

        assertTrue(flow.first().isNotEmpty())
        assertTrue(flow.first().size == 2)
        assertTrue(flow.first()[0].uid == 1)
        assertTrue(flow.first()[1].uid == 2)
    }
}
