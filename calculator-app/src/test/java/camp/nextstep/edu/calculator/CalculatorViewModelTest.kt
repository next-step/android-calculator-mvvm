package camp.nextstep.edu.calculator

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import camp.nextstep.edu.calculator.data.database.RecordDatabase
import camp.nextstep.edu.calculator.data.repository.Injector
import camp.nextstep.edu.calculator.domain.model.Expression
import camp.nextstep.edu.calculator.domain.model.Operator
import camp.nextstep.edu.calculator.domain.model.Record
import camp.nextstep.edu.calculator.domain.repository.RecordRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class CalculatorViewModelTest {
    private lateinit var calculatorViewModel: CalculatorViewModel
    private lateinit var recordRepository: RecordRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val recordDatabase = Room
            .inMemoryDatabaseBuilder(context, RecordDatabase::class.java)
            .build()

        recordRepository = Injector.provideRecordRepository(
            context,
            recordDatabase
        )
        calculatorViewModel = CalculatorViewModel(recordRepository)
    }

    @Test
    fun `입력된 수식이 없을 때 피연산자 1를 입력하면 수식이 1이 된다`() {
        //given

        //when
        calculatorViewModel.addToExpression(1)

        //then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("1")
    }

    @Test
    fun `입력된 피연산자 1이 있을 때 + 연산자를 입력하면 수식이 1 + 가 된다`() {
        //given
        calculatorViewModel.addToExpression(1)

        //when
        calculatorViewModel.addToExpression(Operator.Plus)

        //then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("1 +")
    }

    @Test
    fun `입력된 피연산자 1이 있을 때 피연산자 2를 입력하면 수식이 12 가 된다`() {
        //given
        calculatorViewModel.addToExpression(1)

        //when
        calculatorViewModel.addToExpression(2)

        //then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("12")
    }

    @Test
    fun `입력된 수식 1 + 2 가 있을 때 = 버튼을 누르면 결과가 3이 되어야 한다`() {
        //given
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(2)

        //when
        calculatorViewModel.calculate()

        //then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("3")
    }

    @Test
    fun `입력된 수식 1 + 가 있을 때 = 버튼을 누르면 아무 변화가 없다`() {
        //given
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)

        //when
        calculatorViewModel.calculate()

        //then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("1 +")
    }

    @Test
    fun `입력된 수식 1 + 2 가 있을 때 지우기 버튼을 누르면 결과가 1 + 가 되어야 한다`() {
        //given
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(2)

        //when
        calculatorViewModel.removeLast()

        //then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("1 +")
    }

    @Test
    fun `입력된 수식 12 가 있을 때 지우기 버튼을 누르면 결과가 1 이 되어야 한다`() {
        //given
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(2)

        //when
        calculatorViewModel.removeLast()

        //then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("1")
    }

    @Test
    fun `입력된 수식 1 이 있을 때 지우기 버튼을 누르면 아무 값이 없어야 한다`() {
        //given
        calculatorViewModel.addToExpression(1)

        //when
        calculatorViewModel.removeLast()

        //then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 없을 때 지우기 버튼을 누르면 아무 값이 없어야 한다`() {
        //given

        //when
        calculatorViewModel.removeLast()

        //then
        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo("")
    }

//    @Test
//    fun `저장된 계산 기록을 가져온다`() {
//        //given
//        val record = Record(Expression(listOf("1 + 2")), 3)
//
//        //when
//        Thread(Runnable {
//            runBlocking {
//                withContext(Dispatchers.IO) { calculatorViewModel.saveRecord(record) }
//                withContext(Dispatchers.IO) { calculatorViewModel.loadRecords() }
//            }
//        }).start()
//
//        //then
//        val expected = "${record.expression}\n = ${record.result}"
//        assertThat(calculatorViewModel.expression.value.toString()).isEqualTo(expected)
//    }
}
