package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }
    @Test
    fun `빈 수식일 때, 피연산자를 추가할 수 있어야한다`() {
        // given
        viewModel.addToExpression(1)

        // then
        assertThat(viewModel.result.value).isEqualTo("1")
    }

    @Test
    fun `'8' 수식이 있을 때, 9를 입력하면 89로 바뀌어야 한다`() {
        // given
        viewModel.addToExpression(8)

        // when
        viewModel.addToExpression(9)

        // then
        assertThat(viewModel.result.value).isEqualTo("89")
    }

    @Test
    fun `빈 수식일 때, + 연산자를 추가할 수 없어야 한다`() {
        // given
        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        assertThat(viewModel.result.value).isEqualTo("")
    }

    @Test
    fun `'1' 수식이 있을 때, + 연산자를 추가할 수 있어야 한다`() {
        // given
        viewModel.addToExpression(1)

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        assertThat(viewModel.result.value).isEqualTo("1 +")
    }

    @Test
    fun `'32 + 1' 수식이 있을 때, 마지막 1을 제거할 수 있어야 한다`() {
        // given
        viewModel.addToExpression(3)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)

        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.result.value).isEqualTo("32 +")
    }

    @Test
    fun `'32 +' 수식이 있을 때, 마지막 +를 제거할 수 있어야 한다`() {
        // given
        viewModel.addToExpression(3)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)

        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.result.value).isEqualTo("32")
    }

    @Test
    fun `'32' 수식이 있을 때, 마지막 2를 제거할 수 있어야 한다`() {
        // given
        viewModel.addToExpression(3)
        viewModel.addToExpression(2)

        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.result.value).isEqualTo("3")
    }

    @Test
    fun `'3' 수식이 있을 때, 마지막 3을 제거할 수 있어야 한다`() {
        // given
        viewModel.addToExpression(3)

        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.result.value).isEqualTo("")
    }

    @Test
    fun `빈 수식일 때, 마지막을 제거해도 빈 수식이어야 한다`() {
        // given
        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.result.value).isEqualTo("")
    }

    @Test
    fun `3이 입력되었을 때, 마지막을 제거하면 빈 수식이 된다`() {
        // given
        viewModel.addToExpression(3)

        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.result.value).isEqualTo("")
    }
}