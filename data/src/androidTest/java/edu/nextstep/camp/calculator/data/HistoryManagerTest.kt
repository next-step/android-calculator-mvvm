package edu.nextstep.camp.calculator.data

import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.data.historyStorage.HistoryDatabase
import edu.nextstep.camp.calculator.data.historyStorage.HistoryManager
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class HistoryManagerTest{

    lateinit var historyDatabase: HistoryDatabase
    lateinit var historyManager: HistoryManager

    var historySize = 1

    @Before
    fun setUp() {
        historyDatabase = HistoryDatabase.getInstance(InstrumentationRegistry.getInstrumentation().targetContext)
        historyDatabase.historyDao().deleteAll()
        historyManager = HistoryManager(historyDatabase.historyDao())
    }

    @Test
    fun 계산기록을_저장할_수_있어야_한다() = runBlocking {
        //given
        val expectedSize = 1
        val expression = "2 + 2"
        val result = 4

        //when
        historyManager.insert(expression, result)
        val actualSize = historyManager.getAll().size

        //then
        assertThat(actualSize).isEqualTo(expectedSize)
    }
}