package camp.nextstep.edu.calculator.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import camp.nextstep.edu.calculator.CalculatorViewModel
import camp.nextstep.edu.calculator.data.di.DataInjector
import camp.nextstep.edu.calculator.di.Injector
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.model.Record
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class) // 안드로이드 의존적인 테스트를 애뮬레이터 없이 JVM 상에서 테스트 가능
class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var calculatorViewModel: CalculatorViewModel
    private lateinit var calculatorRepository: CalculatorRepository

    @Before
    fun setUp() {
        calculatorRepository =
            DataInjector.provideCalculatorRepository(ApplicationProvider.getApplicationContext())
        calculatorViewModel =
            Injector.provideCalculatorViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `입력된_피연산자가_없을때_1을_누르면_1이_된다`() {
        // when
        calculatorViewModel.addToExpression(1)

        // then
        val actual = calculatorViewModel.expression.value.toString()
        assertEquals("1", actual)
    }

    @Test
    fun `8이_입력되었을때_9를_입력하면_89가_된다`() {
        // given
        calculatorViewModel.addToExpression(8)

        // when
        calculatorViewModel.addToExpression(9)

        // then
        val actual = calculatorViewModel.expression.value.toString()
        assertEquals("89", actual)
    }

    @Test
    fun `1이_있을때_더하기_버튼을_누르면_1_더하기가_된다`() {
        // given
        calculatorViewModel.addToExpression(1)

        // when
        calculatorViewModel.addToExpression(Operator.Plus)

        // then
        val actual = calculatorViewModel.expression.value.toString()
        assertEquals("1 +", actual)
    }

    @Test
    fun `입력된_수식이_없을때_지우기_버튼을_누르면_아무_변화가_없다`() {
        // when
        calculatorViewModel.removeLast()
        // then
        val actual = calculatorViewModel.expression.value.toString()
        assertEquals("", actual)
    }

    @Test
    fun `32_더하기_1이_있을때_지우기_버튼을_누르면_32_더하기가_된다`() {
        // given
        calculatorViewModel.addToExpression(3)
        calculatorViewModel.addToExpression(2)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(1)

        // when
        calculatorViewModel.removeLast()

        // then
        val actual = calculatorViewModel.expression.value.toString()
        assertEquals("32 +", actual)
    }

    @Test
    fun `32_더하기가_있을때_지우기_버튼을_누르면_32가_된다`() {
        // given
        calculatorViewModel.addToExpression(3)
        calculatorViewModel.addToExpression(2)
        calculatorViewModel.addToExpression(Operator.Plus)

        // when
        calculatorViewModel.removeLast()

        // then
        val actual = calculatorViewModel.expression.value.toString()
        assertEquals("32", actual)
    }

    @Test
    fun `32가_있을때_지우기_버튼을_누르면_3이_된다`() {
        // given
        calculatorViewModel.addToExpression(3)
        calculatorViewModel.addToExpression(2)

        // when
        calculatorViewModel.removeLast()

        // then
        val actual = calculatorViewModel.expression.value.toString()
        assertEquals("3", actual)
    }

    @Test
    fun `3이_있을때_지우기_버튼을_누르면_화면에_아무것도_없게_된다`() {
        // given
        calculatorViewModel.addToExpression(3)

        // when
        calculatorViewModel.removeLast()

        // then
        val actual = calculatorViewModel.expression.value.toString()
        assertEquals("", actual)
    }

    @Test
    fun `3_더하기_2가_있을때_=_버튼을_누르면_5가_된다`() {
        // given
        calculatorViewModel.addToExpression(3)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(2)

        // when
        calculatorViewModel.calculate()

        // then
        val actual = calculatorViewModel.result.value
        assertEquals(5, actual)
    }

    @Test
    fun `3_더하기가_있을때_=_버튼을_누르면_아무_변화가_없다`() {
        // given
        calculatorViewModel.addToExpression(3)
        calculatorViewModel.addToExpression(Operator.Plus)

        // when
        calculatorViewModel.calculate()

        // then
        val actual = calculatorViewModel.result.value
        assertEquals(null, actual)
    }

    @Test
    fun `1_더하기_1가_있을때_=_버튼을_누른뒤_시계_버튼을_누르면_1_더하기_1는_2_기록이_화면에_나온다`() {
        // given
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(1)

        runBlocking {
            withContext(Dispatchers.IO) {
                calculatorViewModel.calculate()
                calculatorRepository.insertRecord(
                    Record(
                        calculatorViewModel.expression.value.toString(),
                        calculatorViewModel.result.value ?: 0
                    )
                )
            }

            val actual = calculatorRepository.getAllRecords().first()
            val expected = listOf(Record("1 + 1", 2))
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `1_더하기가_있을때_시계_버튼을_누른_뒤_다시_시계_버튼을_누르면_1_더하기가_된다`() {
        // given
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.showAllRecords()

        // when
        calculatorViewModel.showPrevExpression()

        // then
        val actual = calculatorViewModel.textInTextView.value
        assertEquals("1 +", actual)
    }

    @Test
    fun `완성된_수식이_있을때_=_버튼을_누른_뒤_1을_누르면_1만_화면에_표시된다`() {
        // given
        calculatorViewModel.addToExpression(1)
        calculatorViewModel.addToExpression(Operator.Plus)
        calculatorViewModel.addToExpression(2)
        calculatorViewModel.calculate()

        // when
        calculatorViewModel.addToExpression(1)

        // then
        val actual = calculatorViewModel.textInTextView.value.toString()
        assertEquals("1", actual)
    }
}