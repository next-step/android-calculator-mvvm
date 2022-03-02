package edu.nextstep.camp.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class MemoryDaoTest {
    private lateinit var memoryDao: MemoryDao
    private lateinit var db: AppDataBase

    @Before
    fun createDb() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            appContext,
            AppDataBase::class.java
        ).build()
        memoryDao = db.memoryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeMemoryAndReadInList() {
        val expression = Expression(listOf(10, Operator.Plus, 20)).toString()
        val result = "30"
        val memory = Memory(expression, result, 1)
        memoryDao.insert(memory)

        val memoryById = memoryDao.getAll()[0]
        assertThat(memoryById).isEqualTo(memory)
    }
}