package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.*
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel(Calculator())
    }

    @Test
    // -> 1 클릭 -> 1
    fun `입력된 피연산자가 없을 때, 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다 (1)`() {
        // when
        viewModel.addToExpression(1)

        // then
        assertThat(viewModel.displayResult.getOrAwaitValue()).isEqualTo("1")
    }

    @Test
    // 5 + -> 1 클릭 -> 5 + 1
    fun `입력된 피연산자가 없을 때, 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다 (2)`() {
        // given
        viewModel.addToExpression(5)
        viewModel.addToExpression(Operator.Plus)

        // when
        viewModel.addToExpression(1)

        // then
        assertThat(viewModel.displayResult.getOrAwaitValue()).isEqualTo("5 + 1")
    }

    @Test
    // 8 -> 9 클릭 -> 89
    fun `입력된 피연산자가 없을 때, 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다`() {
        // given
        viewModel.addToExpression(8)

        // when
        viewModel.addToExpression(9)

        // then
        assertThat(viewModel.displayResult.getOrAwaitValue()).isEqualTo("89")
    }

    @Test
    //  -> + 클릭 ->
    fun `입력된 피연산자가 없을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        // when
        viewModel.addToExpression(Operator.of("+")!!)

        // then
        assertThat(viewModel.displayResult.getOrAwaitValue()).isEmpty()
    }

    @Test
    // 1 -> + 클릭 -> 1 +
    fun `입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다 (1)`() {
        // given
        viewModel.addToExpression(1)

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        assertThat(viewModel.displayResult.getOrAwaitValue()).isEqualTo("1 +")
    }

    @Test
    // 1 + -> / 클릭 -> 1 /
    fun `입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다 (2)`() {
        // given
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)

        // when
        viewModel.addToExpression(Operator.Divide)

        // then
        assertThat(viewModel.displayResult.getOrAwaitValue()).isEqualTo("1 /")
    }

    @Test
    // -> 지우기 클릭 ->
    fun `입력된 수식이 없을 때, 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.displayResult.getOrAwaitValue()).isEmpty()
    }

    @Test
    // 32 + 1 -> 지우기 클릭 -> 32 + -> 지우기 클릭 -> 32 -> 지우기 클릭 -> 3 -> 지우기 클릭 ->  -> 지우기 클릭 ->
    fun `입력된 수식이 있을 때, 사용자가 지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {
        // given
        viewModel.addToExpression(32)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)

        // when
        repeat(4) { viewModel.removeLast() }

        // then
        assertThat(viewModel.displayResult.getOrAwaitValue()).isEmpty()
    }

    @Test
    // 3 + 2 -> = 클릭 -> 5
    fun `입력된 수신이 완전할 때, 사용자가 = 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다`() {
        // given
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)

        // when
        viewModel.calculate()

        // then
        assertThat(viewModel.displayResult.getOrAwaitValue()).isEqualTo("5")
    }
}