package edu.nextstep.camp.calculator.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import edu.nextstep.camp.calculator.domain.CalculationResult
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalculationResultDatabaseTest {
    private lateinit var calculationResultDatabase: CalculationResultDatabase

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("edu.nextstep.camp.calculator.data.test", appContext.packageName)

        calculationResultDatabase = Room.inMemoryDatabaseBuilder(
            appContext,
            CalculationResultDatabase::class.java,
        ).setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .build()
    }

    @Test
    fun `DB에_계산결과를_저장하고_확인_할_수_있다`() = testScope.runTest {
        val entities =
            arrayOf(
                CalculationResultEntity("1 + 1", 2),
                CalculationResultEntity("2 + 2", 4)
            )
        calculationResultDatabase.calculationResultDao().insert(*entities)
        val savedDataList = calculationResultDatabase.calculationResultDao().getAll()
        assert(savedDataList[0].equalsWith(entities[0]))
        assert(savedDataList[1].equalsWith(entities[1]))
    }

    private fun CalculationResultEntity.equalsWith(other: CalculationResultEntity) =
        this.expression == other.expression && this.result == other.result

}