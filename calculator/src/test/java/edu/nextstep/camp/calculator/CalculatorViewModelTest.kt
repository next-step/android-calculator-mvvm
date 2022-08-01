package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.event.Event
import edu.nextstep.camp.calculator.extension.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun 빈_수식에서_숫자를_눌렀을_때_숫자가_보여야한다() {
        //given
        val expected = "1"

        //when
        viewModel.addOperand(1)

        //then
        val actual = viewModel.expression.getOrAwaitValue().toString()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun 입력된_피연산자가_있을_때_피연산자를_입력하면_기존_피연산자_뒤에_합쳐져야_한다() {
        //given
        val expected = "89"
        viewModel.addOperand(8)

        //when
        viewModel.addOperand(9)

        //then
        val actual = viewModel.expression.getOrAwaitValue().toString()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun 입력된_피연산자가_없을_때_연산자를_입력하면_아무런_변화가_없어야_한다() {
        //given
        val expected = ""

        //when
        viewModel.addOperator(Operator.Plus)

        //then
        val actual = viewModel.expression.getOrAwaitValue().toString()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun 입력된_피연산자가_있을_때_연산자를_입력하면_해당_기호가_추가되어야_한다() {
        //given
        val expected = "2 +"
        viewModel.addOperand(2)

        //when
        viewModel.addOperator(Operator.Plus)

        //then
        val actual = viewModel.expression.getOrAwaitValue().toString()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun 입력된_수식이_없을_때_지우기_버튼을_입력하면_아무런_변화가_없어야_한다() {
        //given
        val expected = ""

        //when
        viewModel.removeLast()

        //then
        val actual = viewModel.expression.getOrAwaitValue().toString()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun 입력된_수식이_있을_때_지우기_버튼을_클릭하면_마지막으로_입력된_연산자_또는_피연산자가_지워져야_한다() {
        //given
        val expected = "1"
        viewModel.addOperand(1)
        viewModel.addOperator(Operator.Plus)

        //when
        viewModel.removeLast()

        //then
        val actual = viewModel.expression.getOrAwaitValue().toString()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun 완성된_수식을_계산하면_결과가_화면에_보여야한다() {
        //given
        val expected = "15"
        viewModel.addOperand(5)
        viewModel.addOperator(Operator.Multiply)
        viewModel.addOperand(3)

        //when
        viewModel.calculate()

        //then
        val actual = viewModel.expression.getOrAwaitValue().toString()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun 미완성된_수식을_계산하면_에러메시지를_보여준다() {
        //given
        val expected = Event.Error("완성되지 않은 수식입니다.")
        viewModel.addOperand(1)
        viewModel.addOperator(Operator.Plus)

        //when
        viewModel.calculate()

        //then
        val actual = viewModel.showEvent.getOrAwaitValue()
        assertThat(actual).isEqualTo(expected)
    }
}