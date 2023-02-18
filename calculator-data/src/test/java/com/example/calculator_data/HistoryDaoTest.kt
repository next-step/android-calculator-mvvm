package com.example.calculator_data

import androidx.test.core.app.ApplicationProvider
import com.example.calculator_data.database.HistoryDao
import com.example.calculator_data.database.HistoryEntity
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
class HistoryDaoTest {

    private val historyDao: HistoryDao =
        DaoModule.provideHistoryDao(DatabaseModule.provideDatabase(context = ApplicationProvider.getApplicationContext()))

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    @Test
    fun `아무것도_없을_때_HistoryEntity_를_호출하면_빈_배열을_반환한다`() = testScope.runTest {
        Assert.assertTrue(historyDao.getAll().first().isEmpty())
    }

    @Test
    fun `HistoryEntity를_삽입할_수_있다`() = testScope.runTest {
        val flow = historyDao.getAll()
        Assert.assertTrue(flow.first().isEmpty())
        historyDao.insert(HistoryEntity(statement = "1 + 1", result = 2))

        Assert.assertTrue(flow.first().isNotEmpty())
        Assert.assertTrue(flow.first().size == 1)
        Assert.assertTrue(flow.first()[0].uid == 1)
    }

    @Test
    fun `같은_값을_넣어도_UID를_통해_구분되어_저장된다`() = testScope.runTest {
        val flow = historyDao.getAll()
        Assert.assertTrue(flow.first().isEmpty())
        historyDao.insert(HistoryEntity(statement = "1 + 1", result = 2))
        historyDao.insert(HistoryEntity(statement = "1 + 1", result = 2))

        Assert.assertTrue(flow.first().isNotEmpty())
        Assert.assertTrue(flow.first().size == 2)
        Assert.assertTrue(flow.first()[0].uid == 1)
        Assert.assertTrue(flow.first()[1].uid == 2)
    }
}
