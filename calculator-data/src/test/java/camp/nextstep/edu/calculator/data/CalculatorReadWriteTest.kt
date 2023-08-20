package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class CalculatorReadWriteTest {
    private lateinit var historyDao: HistoryDao
    private lateinit var db: HistoryDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            HistoryDatabase::class.java
        ).build()
        historyDao = db.historyDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    private suspend fun insertAndGet(): HistoryEntity {
        val history = HistoryEntity(expression = "3 + 5", result = "8")
        historyDao.insertAll(history)
        return historyDao.getAll().get(0)
    }

    @Test
    @Throws(IOException::class)
    fun `3 + 5는 8 저장 후 불러옴`() = runTest {
        val actual = insertAndGet()
        assertThat(actual.expression).contains("3 + 5")
        assertThat(actual.result).contains("8")
    }
}
