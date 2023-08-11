package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import camp.nextstep.edu.calculator.data.database.HistoryDatabase
import camp.nextstep.edu.calculator.data.repository.HistoryRepositoryImpl
import camp.nextstep.edu.calculator.domain.model.History
import camp.nextstep.edu.calculator.domain.repository.HistoryRepository
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HistoryRepositoryImplTest {

    private lateinit var database: HistoryDatabase
    private lateinit var repository: HistoryRepository

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, HistoryDatabase::class.java).build()
        repository = HistoryRepositoryImpl(
            historyDao = database.historyDao()
        )
    }

    @After
    fun cleanUp() {
        database.close()
    }

    @Test
    fun `초기값은_빈_리스트다`() {
        runBlocking {
            // given: 초기값

            // then: 빈 리스트가 나와야 한다
            val actual = repository.getHistories()
            assertTrue(actual.isEmpty())
        }
    }

    @Test
    fun `1_더하기_1_결과_저장`() {
        runBlocking {
            // when: 1 + 1 = 저장
            val result = History(expressions = "1 + 1", result = 2)
            repository.insertHistory(result)
        }
    }

    @Test
    fun `1_더하기_1_결과_저장후_해당_연산이_결과값이_저장되야_한다`() {
        val expected = History(expressions = "1 + 1", result = 2)
        runBlocking {
            // when: 1 + 1 = 저장
            repository.insertHistory(expected)

            // then: 1 + 1 = 2가 리스트에 있어야 한다
            val actual = repository.getHistories()
            assertTrue(actual.contains(expected))
        }
    }

    @Test
    fun `2_더하기_2_결과_1_더하기_1_결과_저장후_해당_연산이_결과값이_저장되야_한다`() {
        val expected = listOf(
            History(expressions = "2 + 2", result = 4),
            History(expressions = "1 + 1", result = 2)
        )
        runBlocking {
            // when: 2 + 2 = 4, 1 + 1 = 2 저장
            repository.insertHistory(expected.first())
            repository.insertHistory(expected[1])

            // then: 2 + 2 = 4, 1 + 1 = 2가 리스트에 있어야 한다
            val actual = repository.getHistories()
            assertTrue(actual.containsAll(expected))
        }
    }
}