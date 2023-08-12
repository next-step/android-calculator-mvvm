package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException
import kotlin.concurrent.thread

@RunWith(RobolectricTestRunner::class)
class CalculatorReadWriteTest {
    private lateinit var historyDao: HistoryDao
    private lateinit var db: CalculatorDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            CalculatorDatabase::class.java
        ).build()
        historyDao = db.historyDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun `3 + 5는 8 저장 후 불러옴`() {
        val history = History(expression = "3 + 5", result = "8")
        thread {
            historyDao.insertAll(history)
            val actual = historyDao.getAll()
            assertThat(actual).contains(history)
        }
    }
}
