package edu.nextstep.camp.calculator

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.cash.turbine.test
import edu.nextstep.camp.calculator.data.local.CalculatorDatabase
import edu.nextstep.camp.calculator.data.model.Statement
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class CalculatorDaoTest {
    private lateinit var appDatabase: CalculatorDatabase

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(
            appContext,
            CalculatorDatabase::class.java
        ).build()
    }

    @After
    fun cleanup() {
        appDatabase.close()
    }

    @Test
    fun insertStatement_checkSavedStatement() = runBlocking {
        val statement = Statement(UUID.randomUUID(), "1 + 1", "2")

        val job = launch(start = CoroutineStart.LAZY) {
            appDatabase.calculatorDao().insertStatement(statement)
        }

        appDatabase.calculatorDao().getAll().test {
            job.start()
            Assert.assertEquals(statement, awaitItem().first())
        }
    }
}
