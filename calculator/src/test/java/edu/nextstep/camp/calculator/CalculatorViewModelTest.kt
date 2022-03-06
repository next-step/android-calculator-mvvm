package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.*
import edu.nextstep.camp.calculator.utils.getOrAwaitValue
import edu.nextstep.camp.domain.calculator.CalculatorRepository
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    private lateinit var repository: CalculatorRepository
    private lateinit var viewModel: CalculatorViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = mockk(relaxUnitFun = true)
        viewModel =
            CalculatorViewModel(initialExpression = Expression.EMPTY, repository = repository)
    }

    @Test
    fun `빈 수식에, 숫자가 입력되면, 수식에 추가되고 수식이 갱신된다`() {
        // when :
        viewModel.addToExpression(1)
        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression).isEqualTo(Expression(1))
    }

    @Test
    fun `빈 수식에, 연산자가 입력되면, 수식에 아무런 변화가 없다`() {
        // when :
        viewModel.addToExpression(Operator.Plus)
        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression).isEqualTo(Expression.EMPTY)
    }

    @Test
    fun `수식 1에서, 숫자 2가 입력되면, 수식을 12로 갱신한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(1), repository)
        // when :
        viewModel.addToExpression(2)
        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression).isEqualTo(Expression(12))
    }

    @Test
    fun `수식 1에서, 연산자 +가 입력되면, 수식을 1 + 로 갱신한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(1), repository)
        // when :
        viewModel.addToExpression(Operator.Plus)
        // than :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression).isEqualTo(Expression(1, Operator.Plus))
    }

    @Test
    fun `수식 1 +에서, 연산자 -가 입력되면, 수식을 1 -로 갱신한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(1, Operator.Plus), repository)
        // when :
        viewModel.addToExpression(Operator.Minus)
        // then :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression).isEqualTo(Expression(1, Operator.Minus))
    }

    @Test
    fun `빈 수식에서, 마지막을 제거하면, 수식이 변경되지 않는다`() {
        // when :
        viewModel.removeLast()
        // then :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression).isEqualTo(Expression.EMPTY)
    }

    @Test
    fun `수식 12에서, 마지막을 제거하면, 수식을 1로 갱신한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(12), repository)
        // when :
        viewModel.removeLast()
        // then :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression).isEqualTo(Expression(1))
    }

    @Test
    fun `수식 1 +에서, 마지막을 제거하면, 수식을 1로 갱신한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(1, Operator.Plus), repository)
        // when :
        viewModel.removeLast()
        // then :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression).isEqualTo(Expression(1))
    }

    @Test
    fun `완성된 수식에서, 결과를 구하면, 수식을 결과 값으로 갱신한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(1, Operator.Plus, 2), repository)
        // when :
        viewModel.calculate()
        // then :
        val actualExpression = viewModel.expression.getOrAwaitValue()
        assertThat(actualExpression).isEqualTo(Expression(3))
    }

    @Test
    fun `미완성 수식에서, 결과를 구하면, 잘못된 수식을 알리는 이벤트가 발생한다`() {
        // given :
        val viewModel = CalculatorViewModel(Expression(1, Operator.Plus), repository)
        // when :
        viewModel.calculate()
        // then :
        val actualEvent = viewModel.incompleteExpressionEvent.getOrAwaitValue()
        assertThat(actualEvent.peek()).isTrue()
    }

    @Test
    fun `계산 기록 버튼이 클릭되면, 계산 기록의 Visibility 가 true 가 된다`() {
        // when :
        viewModel.toggleCalculatorMemory()
        // then :
        val actualVisibility = viewModel.calculatorMemoryVisibility.getOrAwaitValue()
        assertThat(actualVisibility).isTrue()
    }
}
