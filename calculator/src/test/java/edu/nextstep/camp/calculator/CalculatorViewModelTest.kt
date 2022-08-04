package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    private lateinit var viewModel: CalculatorViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `빈 수식일 때, 1을 입력하면 1이 화면에 출력된다`() {
        // given
        viewModel = CalculatorViewModel()

        // when
        viewModel.addToExpression(1)

        // then
        assertThat(viewModel.result.value).isEqualTo("1")
    }

    @Test
    fun `'8' 수식이 있을 때, 9를 입력하면 89가 화면에 촐력된다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(8)))

        // when
        viewModel.addToExpression(9)

        // then
        assertThat(viewModel.result.value).isEqualTo("89")
    }

    @Test
    fun `빈 수식일 때, + 를 입력하면 +가 화면에 출력된다`() {
        // given
        viewModel = CalculatorViewModel()

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        assertThat(viewModel.result.value).isEqualTo("")
    }

    @Test
    fun `'1' 수식이 있을 때, + 를 입력하면 화면에 "1 +"가 출력된다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(1)))

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        assertThat(viewModel.result.value).isEqualTo("1 +")
    }

    @Test
    fun `'32' 수식이 있을 때, '3'을 입력하면 화면에 323이 출력된다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(32)))

        // when
        viewModel.addToExpression(3)

        // then
        assertThat(viewModel.result.value).isEqualTo("323")
    }


    @Test
    fun `"32 + 1" 수식이 있을 때, 지우기 버튼을 클릭하면 화면에 "32 +"가 출력된다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(32, Operator.Plus, 1)))

        // when
        viewModel.removeLast()

        // then
        println(viewModel.result)
        assertThat(viewModel.result.value).isEqualTo("32 +")
    }

    @Test
    fun `'32 +' 수식이 있을 때, 지우기 버튼을 클릭하면 화면에 32가 출력된다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(32, Operator.Plus)))

        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.result.value).isEqualTo("32")
    }

    @Test
    fun `'32' 수식이 있을 때, 지우기 버튼을 클릭하면 "3"이 화면에 출력된다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(3, 2)))

        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.result.value).isEqualTo("3")
    }

    @Test
    fun `빈 수식일 때, 지우기 버튼을 클릭하면 그대로 빈 수식이 출력된다`() {
        // given
        viewModel = CalculatorViewModel()

        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.result.value).isEqualTo("")
    }

    @Test
    fun `3이 입력되었을 때, 지우기 버튼을 클릭하면 빈 수식이 출력된다`() {
        // given
        viewModel = CalculatorViewModel(Expression(listOf(3)))

        // when
        viewModel.removeLast()

        // then
        assertThat(viewModel.result.value).isEqualTo("")
    }
}