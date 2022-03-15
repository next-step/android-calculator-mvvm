package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.domain.CalculatorRepository
import edu.nextstep.camp.domain.Operator
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: CalculatorViewModel
    private lateinit var calculatorRepository: CalculatorRepository

    @Before
    fun setUp() {
        calculatorRepository = mockk(relaxed = true)
        viewModel = CalculatorViewModel(calculatorRepository)
    }

    @Test
    fun `수식에 피연산자가 없을 때, 5가 추가되면 수식은 5여야 한다`() {

        //when
        viewModel.addToExpression(5)

        //then
        val acturl: String = viewModel.text.getOrAwaitValue().toString()
        assertThat(acturl).isEqualTo("5")
    }

    @Test
    fun `수식에 5가 있을 때, +가 추가되면 수식은 5 +여야 한다`() {

        //given
        viewModel.addToExpression(5)

        //when
        viewModel.addToExpression(Operator.Plus)

        //then
        val acturl: String = viewModel.text.getOrAwaitValue().toString()
        assertThat(acturl).isEqualTo("5 +")
    }

    @Test // 1 클릭 -> 1
    fun `수식에 피연산자가 없을 때, 0 ~ 9가 추가되면 수식은 0 ~ 9여야 한다 1`() {

        //when
        viewModel.addToExpression(1)

        //then
        val acturl: String = viewModel.text.getOrAwaitValue().toString()
        assertThat(acturl).isEqualTo("1")
    }

    @Test // 5 + -> 1 클릭 -> 5 + 1
    fun `수식에 피연산자가 없을 때, 0 ~ 9가 추가되면 수식은 0 ~ 9여야 한다 2`() {
        //given
        viewModel.addToExpression(5)
        viewModel.addToExpression(Operator.Plus)

        //when
        viewModel.addToExpression(1)

        //then
        val acturl: String = viewModel.text.getOrAwaitValue().toString()
        assertThat(acturl).isEqualTo("5 + 1")
    }

    @Test
    fun `수식에 8이 있을 때 9가 추가되면 수식은 89여야 한다`() {
        //given
        viewModel.addToExpression(8)

        //when
        viewModel.addToExpression(9)

        //then
        val acturl: String = viewModel.text.getOrAwaitValue().toString()
        assertThat(acturl).isEqualTo("89")
    }

    @Test
    fun `수식에 피연산자가 없을 때, +, -, ×, ÷ 를 추가하면 수식은 변화가 없어야 한다`() {

        //when
        viewModel.addToExpression(Operator.Plus)

        //then
        val acturl: String = viewModel.text.getOrAwaitValue().toString()
        assertThat(acturl).isEqualTo("")
    }

    @Test //- 1 + -> - 클릭 -> 1 -
    fun `수식에 피연산자가 있을 때, +, -, ×, ÷ 를 추가하면 수식은 해당 기호 여야 한다`() {

        //given
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)

        //when
        viewModel.addToExpression(Operator.Minus)

        //then
        val acturl: String = viewModel.text.getOrAwaitValue().toString()
        assertThat(acturl).isEqualTo("1 -")
    }

    @Test
    fun `입력된 수식이 없을 때, 지우기를 추가하면 수식은 아무런 변화가 없어야 한다`() {

        //when
        viewModel.removeLast()

        //then
        val acturl: String = viewModel.text.getOrAwaitValue().toString()
        assertThat(acturl).isEqualTo("")
    }

    @Test // 32 + 1 -> 지우기 클릭 -> 32 +
    fun `입력된 수식이 있을 때, 지우기를 추가하면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {

        //given
        viewModel.addToExpression(32)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)

        //when
        viewModel.removeLast()

        //then
        val acturl: String = viewModel.text.getOrAwaitValue().toString()
        assertThat(acturl).isEqualTo("32 +")
    }

    @Test // 3 + 2 -> = 클릭 -> 5
    fun `입력된 수신이 완전할 때, 사용자가 = 을 추가하면 입력된 수식의 결과가 보여야 한다`() {

        //given
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)

        //when
        viewModel.calculator()

        //then
        val acturl: String = viewModel.text.getOrAwaitValue().toString()
        assertThat(acturl).isEqualTo("5")
    }
}

