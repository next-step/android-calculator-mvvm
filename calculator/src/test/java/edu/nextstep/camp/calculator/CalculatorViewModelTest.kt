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

    @Test
    fun `수식 1 +에서, 연산자 -가 입력되면, 수식을 1 -로 갱신한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(1, Operator.Plus))
        // when :
        viewModel.addToExpression(Operator.Minus)
        // then :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("1 -")
    }

    @Test
    fun `빈 수식에서, 마지막을 제거하면, 수식이 변경되지 않는다`() {
        // given :
        val viewModel = CalculatorViewModel(initialExpression = Expression.EMPTY)
        // when :
        viewModel.removeLast()
        // then :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEmpty()
    }

    @Test
    fun `수식 12에서, 마지막을 제거하면, 수식을 1로 갱신한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(12))
        // when :
        viewModel.removeLast()
        // then :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("1")
    }

    @Test
    fun `수식 1 +에서, 마지막을 제거하면, 수식을 1로 갱신한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(1, Operator.Plus))
        // when :
        viewModel.removeLast()
        // then :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("1")
    }

    @Test
    fun `완성된 수식에서, 결과를 구하면, 수식을 결과 값으로 갱신한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(1, Operator.Plus, 2))
        // when :
        viewModel.calculate()
        // then :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression.toString()).isEqualTo("3")
    }

    // 입력된 수신이 완전할 때, 사용자가 = 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다.
    // - 3 + 2 -> = 클릭 -> 5
    // 입력된 수식이 완전하지 않을 때, 사용자가 = 버튼을 눌렀을 때 완성되지 않은 수식입니다 토스트 메세지가 화면에 보여야 한다.
    // - 3 + -> = 클릭 -> 완성되지 않은 수식입니다
}
