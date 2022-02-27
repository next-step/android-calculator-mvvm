package edu.nextstep.camp.calculator.local

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HistoryDaoTest {
    private lateinit var database: CalculatorDatabase
    private lateinit var historyDao: HistoryDao

    private val testHistories = listOf(
        History("1 + 1", "2"),
        History("1 + 1 * 3", "6")
    )

    @Before
    fun setUp()  {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(appContext, CalculatorDatabase::class.java).build()
        historyDao = database.getHistoryDao()

        historyDao.insert(testHistories)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `저장되어_있는_모든_history_목록이_나온다`() {

        val actual = historyDao.getAll()

        assertThat(actual).containsExactly(History(1,"1 + 1", "2"),   History(2,"1 + 1 * 3", "6"))
    }

    @Test
    fun `history가_정상적으로_저장된다`() {
        val insertHistory = History(3, "1 + 4 + 6 - 2", "9")

        historyDao.insert(listOf(insertHistory))

        val actual = historyDao.getAll()

        assertThat(actual).contains(insertHistory)
    }
}