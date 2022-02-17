package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.*
import edu.nextstep.camp.calculator.utils.getOrAwaitValue
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `빈 수식에, 숫자가 입력되면, 수식에 추가되고 수식이 갱신된다`() {
        // given :
        val viewModel = CalculatorViewModel(initialExpression = Expression.EMPTY)
        // when :
        viewModel.addToExpression(1)
        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("1")
    }

    @Test
    fun `빈 수식에, 연산자가 입력되면, 수식에 아무런 변화가 없다`() {
        // given :
        val viewModel = CalculatorViewModel(initialExpression = Expression.EMPTY)
        // when :
        viewModel.addToExpression(Operator.Plus)
        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEmpty()
    }

    @Test
    fun `수식 1에서, 숫자 2가 입력되면, 수식을 12로 갱신한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(1))
        // when :
        viewModel.addToExpression(2)
        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("12")
    }

    @Test
    fun `수식 1에서, 연산자 +가 입력되면, 수식을 1 + 로 갱신한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(1))
        // when :
        viewModel.addToExpression(Operator.Plus)
        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("1 +")
    }
    // 입력된 피연산자가 없을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 화면에 아무런 변화가 없어야 한다.
    // -  -> + 클릭 ->
    // 입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다.
    // - 1 -> + 클릭 -> 1 +
    // - 1 + -> - 클릭 -> 1 -
    // 입력된 수식이 없을 때, 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다.
    // -  -> 지우기 클릭 ->
    // 입력된 수식이 있을 때, 사용자가 지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다.
    // - 32 + 1 -> 지우기 클릭 -> 32 + -> 지우기 클릭 -> 32 -> 지우기 클릭 -> 3 -> 지우기 클릭 ->  -> 지우기 클릭 ->
    // 입력된 수신이 완전할 때, 사용자가 = 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다.
    // - 3 + 2 -> = 클릭 -> 5
    // 입력된 수식이 완전하지 않을 때, 사용자가 = 버튼을 눌렀을 때 완성되지 않은 수식입니다 토스트 메세지가 화면에 보여야 한다.
    // - 3 + -> = 클릭 -> 완성되지 않은 수식입니다
}
