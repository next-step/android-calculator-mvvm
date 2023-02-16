package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.Operator
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModelTest: CalculatorViewModel

    @Before
    fun setUp() {
        viewModelTest = CalculatorViewModel()
    }

    @Test
    fun `입력된 피연산자가 없을 때, 사용자가 피연산자 버튼을 누르면 화면에 해당 숫자가 화면에 보여야한다_1`() {
        // 빈 수식 -> 1 클릭 -> 1
        // when
        viewModelTest.addOperand(1)

        // then
        val actual = viewModelTest.result.getOrAwaitValue()
        assertEquals("1", actual)
    }

    @Test
    fun `입력된 피연산자가 없을 때, 사용자가 피연산자 버튼을 누르면 화면에 해당 숫자가 화면에 보여야한다_2`() {
        // 5 + -> 1 클릭 -> 5 + 1
        // given
        viewModelTest.addOperand(5)
        viewModelTest.addOperator(Operator.Plus)

        // when
        viewModelTest.addOperand(1)

        // then
        val actual = viewModelTest.result.getOrAwaitValue()
        assertEquals("5 + 1", actual)
    }

    @Test
    fun `입력된 피안션자가 존재할 때, 기존 숫자 뒤에 해당 숫자가 화면에 보여야한다`() {
        // 8 -> 9 클릭 -> 89
        // given
        viewModelTest.addOperand(8)

        // when
        viewModelTest.addOperand(9)

        // then
        val actual = viewModelTest.result.getOrAwaitValue()
        assertEquals("89", actual)
    }

    @Test
    fun `입력된 피연산자가 없을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        // 빈수식 -> + 클릭 -> 빈수식
        // when
        viewModelTest.addOperator(Operator.Plus)

        // then
        val actual = viewModelTest.result.getOrAwaitValue()
        assertEquals("", actual)
    }

    @Test
    fun `입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다_1`() {
        // 1 -> + 클릭 -> 1 +
        // given
        viewModelTest.addOperand(1)

        // when
        viewModelTest.addOperator(Operator.Plus)

        // then
        val actual = viewModelTest.result.getOrAwaitValue()
        assertEquals("1 +", actual)
    }

    @Test
    fun `입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다_2`() {
        // 1 + -> - 클릭 -> 1 -
        // given
        viewModelTest.addOperand(1)
        viewModelTest.addOperator(Operator.Plus)

        // when
        viewModelTest.addOperator(Operator.Minus)

        // then
        val actual = viewModelTest.result.getOrAwaitValue()
        assertEquals("1 -", actual)
    }

    @Test
    fun `입력된 수식이 없을 때, 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다`() {
        // 빈수식 -> 지우기 클릭 -> 빈수식
        // when
        viewModelTest.removeLast()

        // then
        val actual = viewModelTest.result.getOrAwaitValue()
        assertEquals("", actual)
    }

    @Test
    fun `입력된 수식이 있을 때, 사용자가 지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다`() {
        // 32 + 1 -> 지우기 클릭 -> 32 + -> 지우기 클릭 -> 32 -> 지우기 클릭 -> 3 -> 지우기 클릭 -> 빈수식 -> 지우기 클릭 -> 빈수식
        // given
        viewModelTest.addOperand(3)
        viewModelTest.addOperand(2)
        viewModelTest.addOperator(Operator.Plus)
        viewModelTest.addOperand(1)

        // when
        viewModelTest.removeLast()

        // then
        val firstRemovedActual = viewModelTest.result.getOrAwaitValue()
        assertEquals("32 +", firstRemovedActual)

        // when
        viewModelTest.removeLast()

        // then
        val secondRemovedActual = viewModelTest.result.getOrAwaitValue()
        assertEquals("32", secondRemovedActual)

        // when
        viewModelTest.removeLast()

        // then
        val thirdRemovedActual = viewModelTest.result.getOrAwaitValue()
        assertEquals("3", thirdRemovedActual)

        // when
        viewModelTest.removeLast()

        // then
        val fourthRemovedActual = viewModelTest.result.getOrAwaitValue()
        assertEquals("", fourthRemovedActual)

        // when
        viewModelTest.removeLast()

        // then
        val fifthRemovedActual = viewModelTest.result.getOrAwaitValue()
        assertEquals("", fifthRemovedActual)
    }

    @Test
    fun `입력된 수신이 완전할 때, 사용자가 = 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다`() {
        // 3 + 2 -> = 클릭 -> 5
        // given
        viewModelTest.addOperand(3)
        viewModelTest.addOperator(Operator.Plus)
        viewModelTest.addOperand(2)

        // when
        viewModelTest.calculate()

        // then
        val actual = viewModelTest.result.getOrAwaitValue()
        assertEquals("5", actual)
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, 사용자가 = 버튼을 눌렀을 때 완성되지 않은 수식입니다 토스트 메세지가 화면에 보여야 한다`() {
        // 3 + -> = 클릭 -> 완성되지 않은 수식입니다 라는 토스트 메시지가 뜬 뒤, 표현식 변화가 없어어야 한다.
        // given
        viewModelTest.addOperand(3)
        viewModelTest.addOperator(Operator.Plus)

        // when
        viewModelTest.calculate()

        // then
        val actual = viewModelTest.result.getOrAwaitValue()
        assertEquals("3 +", actual)
    }

    @Test
    fun `피연산자만 입력한 상태에서 이퀄사인 누른 뒤, 피연산자를 누렀을 시, 피연산자가 합쳐진 결과가 나와야 한다`() {
        // given
        viewModelTest.addOperand(3)
        viewModelTest.calculate()


        // when
        viewModelTest.addOperand(3)

        // then
        val actual = viewModelTest.result.getOrAwaitValue()
        assertEquals("33", actual)
    }
}