package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by link.js on 2022. 08. 02..
 */

@RunWith(AndroidJUnit4::class)
class HistoryDaoTest {
    private lateinit var historyDao: HistoryDao
    private lateinit var db: AppDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        historyDao = db.historyDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAllTest() = runBlocking {
        // given
        historyDao.insertAll(listOf(History(expression = "2 + 2", result = 4)))
        val histories = historyDao.getAll()

        // then
        assertEquals(1, histories.size)
    }

    @Test
    fun setHistoriesTest() = runBlocking {
        // given
        historyDao.insertAll(listOf(History(expression = "2 + 2", result = 4)))
        historyDao.setHistories(listOf(History(expression = "2 + 2", result = 4),
            History(expression = "2 + 5", result = 7)
        ))
        val histories = historyDao.getAll()

        // then
        assertEquals(2, histories.size)
    }

    @Test
    fun deleteAllTest() = runBlocking {
        historyDao.insertAll(listOf(History(expression = "2 + 2", result = 4)))
        historyDao.deleteAll()
        val histories = historyDao.getAll()

        // then
        assertEquals(0, histories.size)
    }
}