package camp.nextstep.edu.calculator.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import camp.nextstep.edu.calculator.data.db.CalculatorDatabase
import camp.nextstep.edu.calculator.domain.model.CalculatorResult
import camp.nextstep.edu.calculator.domain.repository.ResultRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors


@RunWith(AndroidJUnit4::class)
class ResultRepositoryImplTest {

    private lateinit var database: CalculatorDatabase

    private lateinit var repository: ResultRepository


    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room
            .inMemoryDatabaseBuilder(context, CalculatorDatabase::class.java)
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()

        repository = ResultRepositoryImpl(database)
    }

    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun `저장`() {
        val result = CalculatorResult("1 + 1", "2")
        repository.saveResult(result)
    }

    @Test
    fun `불러오기`() {
        runBlocking {
            val expected = listOf(CalculatorResult("1 + 1", "2"))
            launch(Dispatchers.IO) {
                repository.saveResult(expected.first())
                repository.getAllResults().collect { actual ->
                    Truth.assertThat(actual).isEqualTo(expected)
                }
            }.cancel()
        }
    }
}
