package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MemoryEntityDbTest {

    private lateinit var memoryDao: MemoryDao
    private lateinit var db: MemoryDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        db = Room.inMemoryDatabaseBuilder(context, MemoryDatabase::class.java)
            .build()
        memoryDao = db.resultDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertMemoryGetAllListMemoryItem() {
        val memoryEntity = MemoryEntity(expression = "44 + 3", result = "47")

        memoryDao.insert(memoryEntity)
        val resultList = memoryDao.getAll()
        val actual = resultList[resultList.size - 1]

        assertThat(actual, equalTo(memoryEntity))
    }
}
