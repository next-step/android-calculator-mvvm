package edu.nextstep.camp.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.data.database.CalculatorDao
import edu.nextstep.camp.data.database.CalculatorDatabase
import edu.nextstep.camp.data.entity.MemoryEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CalculatorDaoTest {

    private lateinit var calculatorDatabase: CalculatorDatabase
    private lateinit var calculatorDao: CalculatorDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        calculatorDatabase = Room.inMemoryDatabaseBuilder(
            context,
            CalculatorDatabase::class.java
        ).build()

        calculatorDao = calculatorDatabase.calculatorDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        calculatorDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun `단일_MemoryEntity_저장_후_가져오기`() {
        // when
        val memoryEntities = listOf(
            MemoryEntity(expression = "3 + 5", result = 8)
        )

        memoryEntities.forEach {
            calculatorDao.addMemory(it)
        }

        // then
        val actual = calculatorDao.getMemoryList()

        assertThat(actual).isEqualTo(memoryEntities)
    }

    @Test
    @Throws(Exception::class)
    fun `복수_MemoryEntity_저장_후_가져오기`() {
        // when
        val memoryEntities = listOf(
            MemoryEntity(expression = "3 + 5", result = 8),
            MemoryEntity(expression = "10 - 5", result = 5),
            MemoryEntity(expression = "2 * 8", result = 16),
            MemoryEntity(expression = "20 / 4", result = 5)
        )

        memoryEntities.forEach {
            calculatorDao.addMemory(it)
        }

        // then
        val actual = calculatorDao.getMemoryList()

        assertThat(actual).isEqualTo(memoryEntities)
    }
}