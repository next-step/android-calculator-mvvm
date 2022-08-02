package edu.nextstep.camp.calculator.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 02..
 */
@RunWith(AndroidJUnit4::class)
class CalculatorRepositoryTest {

    private lateinit var appDatabase: CalculatorDatabase

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        appDatabase = Room.inMemoryDatabaseBuilder(
            appContext,
            CalculatorDatabase::class.java
        ).build()
    }

    @Test
    fun insertCalculationRecord_checkCalculationRecord() = runBlocking {
        // given
        // when
        val calculationRecordTmp = CalculationRecord("1 + 1", 2, 0)
        appDatabase.calculationRecordsDao().insert(calculationRecordTmp)
        val calculationRecord = appDatabase.calculationRecordsDao().getAll().getOrNull(0)

        // then
        assertEquals(calculationRecord?.expression, calculationRecordTmp.expression)
        assertEquals(calculationRecord?.result, calculationRecordTmp.result)
    }


    @After
    fun cleanup() {
        appDatabase.close()
    }
}