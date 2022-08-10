package edu.nextstep.camp.calculator.data.historyStorage

import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

internal class HistoryManagerTest{

    @JvmField
    @RegisterExtension
    val instantTaskExecutorExtension = InstantTaskExecutorExtension()

    lateinit var historyDatabase: HistoryDatabase
    lateinit var historyManager: HistoryManager

    @BeforeEach
    fun setUp() {
        historyDatabase = HistoryDatabase.getInstance(mockk(relaxed = true))
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