package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.Operator
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

internal class CalculatorViewModelTest {

    private val viewModel = CalculatorViewModel()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `1을_누르면_1이_보인다`() {
        // when: 1을 누르면
        viewModel.addToExpression(1)

        // then: 1이 나온다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `12을_누르면_12이_보인다`() {
        // when: 12을 누르면
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)

        // then: 12이 나온다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("12")
    }

    @Test
    fun `12_더하기를_누르면_12더하기가_보인다`() {
        // when: 12 +을 누르면
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)

        // then: 12 + 이 나온다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("12 +")
    }

    @Test
    fun `12더하기을_누르고_12를_누르면_12더하기12이_보인다`() {
        // given: 12 +
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)

        // when: 12를 누르면
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)

        // then: 12 + 12이 나온다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("12 + 12")
    }

    @Test
    fun `12더하기12을_누르고_결과를_누르면_24_보인다`() {
        // given: 12 + 12
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)

        // when: 결과를 누르면
        viewModel.calculate()

        // then: 24이 나온다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("24")
    }

    @Test
    fun `12을_누르고_지우기_누르면_1이_보인다`() {
        // given: 12을 누르면
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)

        // when: 지우기를 누르면
        viewModel.removeLast()

        // then: 1이 나온다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `12더하기12결과_누르고_리셋버튼을_누르면_빈문자열_보인다`() {
        // given: 12 + 12 =
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)
        viewModel.addToExpression(2)
        viewModel.calculate()

        // when: 리셋 누르면
        viewModel.removeMemory()

        // then: 빈문자열이 나온다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("")
    }

    @Test
    fun `초기상태에서_더하기를_누르면_동작을_안한다`() {
        // when: +를 누르면
        viewModel.addToExpression(Operator.Plus)

        // then: 동작을 안한다
        val actual = viewModel.text.getOrAwaitValue()
        assertThat(actual).isEqualTo("")
    }
}