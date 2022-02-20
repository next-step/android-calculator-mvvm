package edu.nextstep.camp.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import java.io.IOException
import org.junit.jupiter.api.Test

class MemoryDaoTest {
    private lateinit var memoryDao: MemoryDao
    private lateinit var db: AppDataBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
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
    suspend fun writeMemoryAndReadInList() = withContext(Dispatchers.IO) {
        val expression = Expression(listOf(10, Operator.Plus, 20)).toString()
        val result = "30"
        val memory = Memory(expression, result, 0)
        memoryDao.insert(memory)

        val memoryById = memoryDao.getMemoryById(0)
        assertThat(memoryById).isEqualTo(memory)
    }
}