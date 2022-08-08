package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CalculatorDbTest {
    private lateinit var userDao: EvaluationRecordDao
    private lateinit var db: CalculatorDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, CalculatorDatabase::class.java).build()
        userDao = db.evaluationRecordDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testWriteAndReadEvaluationRecord() {
        val entity = EvaluationRecordEntity(1, "1 + 1", "2")
        runBlocking {
            userDao.insert(entity)
            val history = userDao.getAll().firstOrNull()
            assertThat(history).isEqualTo(listOf(entity))
        }
    }
}
