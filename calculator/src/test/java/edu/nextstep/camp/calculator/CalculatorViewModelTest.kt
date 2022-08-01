package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.CalculatorEvent.*
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    lateinit var viewModel: CalculatorViewModel

    @Test
    fun `빈 수식에 피연산자 1을 추가 하면 수식에 해당 피연산자 1이 추가된다`() {
        // given 수식이 빈상태에서
        viewModel = CalculatorViewModel(lastExpression = Expression.EMPTY)

        // when 피연산자 1이 추가 되면
        viewModel.addOperandToExpression(1)

        // then 수식에 해당 피연산자가 추가된다.
        val expected = Expression(listOf(1))
        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `피연산자 1이 마지막으로 입력된 수식에 피연산자 2을 추가 하면 수식의 마지막 피연산자는 12로 변경된다`() {
        // given 수식에 1이 입력된 상태에서
        val inputtedExpression = Expression(listOf(1))
        viewModel = CalculatorViewModel(lastExpression = inputtedExpression)

        // when 피연산자 2가 추가 되면
        viewModel.addOperandToExpression(2)

        // then 수식의 마지막 피연산자는 12가 변경된다.
        val expected = Expression(listOf(12))
        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `피연산자 1이 마지막으로 입력된 수식에 연산자 +를 추가 하면 수식은 1 + 로 표현된다`() {
        // given 수식에 피연산자 1이 추가된 상태
        val inputtedExpression = Expression(listOf(1))
        viewModel = CalculatorViewModel(lastExpression = inputtedExpression)

        // when 연산자 + 가 추가되면
        viewModel.addOperatorToExpression(Operator.Plus)

        // then 수식은 1 + 로 표현된다.
        val expected = Expression(listOf(1, Operator.Plus))
        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `수식의 마지막 연산자 +가 일때 수식에 연산자 - 를 추가 하면 수식의 마지막 연산자가 - 로 변경된다`() {
        // given 수식이 1 + 일 때
        val inputtedExpression = Expression(listOf(1, Operator.Plus))
        viewModel = CalculatorViewModel(lastExpression = inputtedExpression)

        // when 연산자 - 가 추가되면
        viewModel.addOperatorToExpression(Operator.Minus)

        // then 수식은 1 - 로 표현된다.
        val expected = Expression(listOf(1, Operator.Minus))
        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `빈 수식에 연산자를 추가 하면 빈 수식을 유지 한다`() {
        // given 빈 수식인 경우
        val inputtedExpression = Expression.EMPTY
        viewModel = CalculatorViewModel(lastExpression = inputtedExpression)

        // when 연산자 - 가 추가되면
        viewModel.addOperatorToExpression(Operator.Minus)

        // then 수식은 빈 수식을 유지 한다.
        val expected = Expression.EMPTY
        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `12 + 34 인 수식에 삭제 요청시 수식은 마지막에 위치한 피연산자 4가 삭제 된 12 + 3의 형태를 가진다`() {
        // given 수식이 12 + 34 인 경우
        val inputtedExpression = Expression(listOf(12, Operator.Plus, 34))
        viewModel = CalculatorViewModel(lastExpression = inputtedExpression)

        // when 삭제를 요청 할 경우
        viewModel.removeLastFromExpression()

        // then 수식은 12 + 3의 형태를 가진다
        val expected = Expression(listOf(12, Operator.Plus, 3))
        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `12 +인 수식에 삭제 요청시 수식은 마지막에 위치한 연산자 + 가 삭제 된 12의 형태를 가진다`() {
        // given 수식이 12 + 인 경우
        val inputtedExpression = Expression(listOf(12, Operator.Plus))
        viewModel = CalculatorViewModel(lastExpression = inputtedExpression)

        // when 삭제를 요청 할 경우
        viewModel.removeLastFromExpression()

        // then 수식은 12의 형태를 가진다
        val expected = Expression(listOf(12))
        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `빈 수식일 때 삭제 요청시 빈 수식을 유지 한다`() {
        // given 빈 수식인 경우
        val inputtedExpression = Expression.EMPTY
        viewModel = CalculatorViewModel(lastExpression = inputtedExpression)

        // when 삭제를 요청 할 경우
        viewModel.removeLastFromExpression()

        // then 수식은 빈 수식을 유지 한다.
        val expected = Expression.EMPTY
        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `완전한 수식일 때 계산 요청시 계산 결과를 전달한다`() {
        // given 완전한 수식인 경우
        val inputtedExpression = Expression(listOf(12, Operator.Plus, 34))
        viewModel = CalculatorViewModel(lastExpression = inputtedExpression)

        // when 계산요청시
        viewModel.requestCalculate()

        // then 수식의 결과 값을 전달 한다.
        val expected = Expression(listOf(46))
        val actual = viewModel.expression.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `연산자로 끝나는 불완전한 수식일 때 계산 요청시 '완성되지 않은 수식'에러 이벤트를 발생시킨다`() {
        // given 연산자로 끝나는 불완전한 수식인 경우
        val inputtedExpression = Expression(listOf(12, Operator.Plus))
        viewModel = CalculatorViewModel(lastExpression = inputtedExpression)

        // when 계산요청시
        viewModel.requestCalculate()

        // then '완성되지 않은 수식' 이벤트를 발생시킨다.
        val expected = ERROR_INCOMPLETE_EXPRESSION
        val actual = viewModel.event.getOrAwaitValue().consume()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `피연산자 하나만 있는 불완전한 수식일 때 계산 요청시 '완성되지 않은 수식'에러 이벤트를 발생시킨다`() {
        // given 연산자로 끝나는 불완전한 수식인 경우
        val inputtedExpression = Expression(listOf(12))
        viewModel = CalculatorViewModel(lastExpression = inputtedExpression)

        // when 계산요청시
        viewModel.requestCalculate()

        // then '완성되지 않은 수식' 이벤트를 발생시킨다.
        val expected = ERROR_INCOMPLETE_EXPRESSION
        val actual = viewModel.event.getOrAwaitValue().consume()
        assertThat(actual).isEqualTo(expected)
    }

}