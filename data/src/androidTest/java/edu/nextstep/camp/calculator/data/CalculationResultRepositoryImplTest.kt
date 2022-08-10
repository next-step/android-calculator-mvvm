package edu.nextstep.camp.calculator.data

import androidx.test.platform.app.InstrumentationRegistry
import edu.nextstep.camp.calculator.domain.CalculationResult
import edu.nextstep.camp.calculator.domain.CalculationResultRepository
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
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
class CalculationResultRepositoryImplTest {
    private lateinit var repositoryImpl: CalculationResultRepository

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("edu.nextstep.camp.calculator.data.test", appContext.packageName)

        repositoryImpl =
            DataRepositoryInjector.provideCalculationResultRepository(appContext)
    }

    @Test
    fun `repository에_계산결과를_저장하고_확인_할_수_있다`() = testScope.runTest {
        val entities =
            arrayOf(
                CalculationResult(Expression(listOf(1, Operator.Plus, 1)), 2),
                CalculationResult(Expression(listOf(2, Operator.Plus, 2)), 4)
            )
        repositoryImpl.insert(*entities)
        val savedDataList = repositoryImpl.getAll()
        assert(savedDataList[0] == entities[0])
        assert(savedDataList[1] == entities[1])
    }

}