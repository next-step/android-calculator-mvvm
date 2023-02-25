package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.Operator
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class CalculatorViewModelTest {
    private lateinit var calculatorViewModel: CalculatorViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        calculatorViewModel = CalculatorViewModel()
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
}