package edu.nextstep.camp.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.cash.turbine.test
import com.google.common.truth.Truth.*
import edu.nextstep.camp.data.db.CalculatorDatabase
import edu.nextstep.camp.data.model.CalculatorRecordEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorRecordDaoTest {

    private lateinit var database: CalculatorDatabase

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(
            appContext,
            CalculatorDatabase::class.java
        ).build()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun 계산_기록이_추가되면_데이터베이스에_저장된다() = runBlocking {
        // given :
        val record = CalculatorRecordEntity(1, "1 + 2", "3")
        // when :
        database.calculatorRecordDao().insertRecord(record)
        // then :
        database.calculatorRecordDao().getAllRecord().test {
            assertThat(record).isEqualTo(awaitItem().first())
        }
    }
}
