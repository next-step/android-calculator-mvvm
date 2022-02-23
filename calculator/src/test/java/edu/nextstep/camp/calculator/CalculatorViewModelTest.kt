package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setup() {
        viewModel = CalculatorViewModel()
    }

    //입력된 피연산자가 없을 때, 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다.
    //-  -> 1 클릭 -> 1
    //- 5 + -> 1 클릭 -> 5 + 1
    @Test
    fun `수식에 피연자가 없을 때, 1이 추가되면, 텍스트는 1이어야 한다`() {
        // when
        viewModel.addToExpression(1)

        // then
        val actual = viewModel.getText()
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun `수식에 5 +가 있을 때, 1 이 추가되면, 텍스트는 '5 + 1' 이어여야 한다`() {
        // given
        viewModel.addToExpression(5)
        viewModel.addToExpression(Operator.Plus)

        // when
        viewModel.addToExpression(1)

        // then
        val actual = viewModel.getText()
        assertThat(actual).isEqualTo("5 + 1")
    }


    //입력된 피연산자가 있을 때, 기존 숫자 뒤에 해당 숫자가 화면에 보여야 한다. 예를 들면, 8이 입력되어 있을 때 9를 입력하면 89가 보여야 한다.
    //- 8 -> 9 클릭 -> 89
    @Test
    fun `수식에 8이 있을 때, 9 가 추가 되면 텍스트는 '89' 이어야 한다`() {
        // given
        viewModel.addToExpression(8)

        // when
        viewModel.addToExpression(9)

        // then
        val actual = viewModel.getText()
        assertThat(actual).isEqualTo("89")
    }

    //입력된 피연산자가 없을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 화면에 아무런 변화가 없어야 한다.
    //-  -> + 클릭 ->
    @Test
    fun `수식이 비어있을 때, + 를 추가하면 텍스트는 비어있어야 한다`() {
        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        val actual = viewModel.getText()
        assertThat(actual).isEqualTo("")
    }

    //입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다.
    //- 1 -> + 클릭 -> 1 +
    //- 1 + -> - 클릭 -> 1 -
    @Test
    fun `수식에 1이 있을 때, + 가 추가되면 텍스트는 '1 +' 여야 한다`() {
        // given
        viewModel.addToExpression(1)

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        val actual = viewModel.getText()
        assertThat(actual).isEqualTo("1 +")
    }

    @Test
    fun `수식이 '1 +' 일 때, '-' 를 추가하면 텍스트는 '1 -'가 되어야 한다`() {
        // given
        viewModel.addToExpression(1)
        viewModel.addToExpression(Operator.Plus)

        // when
        viewModel.addToExpression(Operator.Minus)

        // then
        val actual = viewModel.getText()
        assertThat(actual).isEqualTo("1 -")
    }


    //입력된 수식이 없을 때, 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다.
    //-  -> 지우기 클릭 ->
    @Test
    fun `수식이 비어있을 때, 마지막을 제거하면 텍스트는 비어있어야 한다`() {
        // when
        viewModel.removeLast()

        // then
        val actual = viewModel.getText()
        assertThat(actual).isEqualTo("")
    }

    //입력된 수식이 있을 때, 사용자가 지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다.
    //- 32 + 1 -> 지우기 클릭 -> 32 + -> 지우기 클릭 -> 32 -> 지우기 클릭 -> 3 -> 지우기 클릭 ->  -> 지우기 클릭 ->
    @Test
    fun `수식이 '32 + 1'일 때, 마지막을 제거하면, 텍스트는 '32 +' 여야 한다`() {
        // given
        viewModel.addToExpression(32)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(1)

        // when
        viewModel.removeLast()

        // then
        val actual = viewModel.getText()
        assertThat(actual).isEqualTo("32 +")

        // when
        viewModel.removeLast()
        // then
        assertThat(viewModel.getText()).isEqualTo("32")

        // when
        viewModel.removeLast()
        // then
        assertThat(viewModel.getText()).isEqualTo("3")

        // when
        viewModel.removeLast()
        // then
        assertThat(viewModel.getText()).isEqualTo("")

        // when
        viewModel.removeLast()
        // then
        assertThat(viewModel.getText()).isEqualTo("")
    }

    //입력된 수신이 완전할 때, 사용자가 = 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다.
    //- 3 + 2 -> = 클릭 -> 5
    @Test
    fun `수식이 '3 + 2'일 때, 계산하면, 텍스트는 '5' 이어야 한다`() {
        // given
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(2)

        // when
        viewModel.calculate()

        // then
        val actual = viewModel.getText()
        assertThat(actual).isEqualTo("5")
    }

    //입력된 수식이 완전하지 않을 때, 사용자가 = 버튼을 눌렀을 때 완성되지 않은 수식입니다 토스트 메세지가 화면에 보여야 한다.
    //- 3 + -> = 클릭 -> 완성되지 않은 수식입니다
    @Test
    fun `수식이 '3 +' 일 때, 계산하면, 에러 메시지 이벤트를 발생시켜야한다`() {
        // given
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Plus)

        // when
        viewModel.calculate()

        // then
        val actual = viewModel.getToastMessage()
        assertThat(actual).isEqualTo("완성되지 않은 수식입니다")
    }

    private fun CalculatorViewModel.getText(): String {
        return text.getOrAwaitValue()
    }

    private fun CalculatorViewModel.getToastMessage(): String {
        return toastMessage.getOrAwaitValue()
    }
}
