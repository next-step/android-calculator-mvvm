package edu.nextstep.camp.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class CalculationRecordsDaoTest {

    private lateinit var dao: CalculationRecordsDao

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        dao = AppDatabase.getInstance(appContext).calculationRecordDao()
    }

    @Test
    fun `insert를_하면_계산기록은_저장이_된다`() = runTest {
        //given
        val expected1 = CalculationRecordEntity(Expression(listOf("1", Operator.Plus, "2")), 3)
        val expected2 = CalculationRecordEntity(Expression(listOf("1", Operator.Plus, "3")), 4)

        //when
        dao.insertCalculationRecords(expected1)
        val actual1 = dao.loadCalculationRecords().last()

        //then
        assertThat(actual1.expression.toString()).isEqualTo(expected1.expression.toString())
        assertThat(actual1.result).isEqualTo(expected1.result)

        //when
        dao.insertCalculationRecords(expected2)
        val actual2 = dao.loadCalculationRecords().last()

        //then
        assertThat(actual2.expression.toString()).isEqualTo(expected2.expression.toString())
        assertThat(actual2.result).isEqualTo(expected2.result)
    }

    @Test
    fun `load를_하면_저장되었던_계산기록을_가져온다`() = runTest {
        //given
        val expected1 = CalculationRecordEntity(Expression(listOf("1", Operator.Plus, "2")), 3)
        val expected2 = CalculationRecordEntity(Expression(listOf("1", Operator.Plus, "4")), 5)
        val expected3 = CalculationRecordEntity(Expression(listOf("1", Operator.Plus, "6")), 7)

        //when
        dao.insertCalculationRecords(expected1)
        dao.insertCalculationRecords(expected2)
        dao.insertCalculationRecords(expected3)
        val actual = dao.loadCalculationRecords()

        //then
        assertThat(actual.last().expression.toString()).isEqualTo(expected3.expression.toString())
        assertThat(actual.last().result).isEqualTo(expected3.result)

        assertThat(actual[actual.size - 2].expression.toString()).isEqualTo(expected2.expression.toString())
        assertThat(actual[actual.size - 2].result).isEqualTo(expected2.result)

        assertThat(actual[actual.size - 3].expression.toString()).isEqualTo(expected1.expression.toString())
        assertThat(actual[actual.size - 3].result).isEqualTo(expected1.result)
    }
}