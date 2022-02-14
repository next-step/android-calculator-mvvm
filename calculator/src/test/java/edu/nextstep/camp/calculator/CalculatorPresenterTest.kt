package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CalculatorPresenterTest {
    private lateinit var presenter: CalculatorPresenter
    private lateinit var view: CalculatorContract.View

    @Before
    fun setUp() {
        view = mockk()
        presenter = CalculatorPresenter(view)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("1 +")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<Expression>()
        every { view.showExpression(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addToExpression(1)
        presenter.removeLast()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.toString()).isEqualTo("")
        verify { view.showExpression(actual) }
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        every { view.showExpression(any()) } answers { nothing }
        every { view.showResult(any()) } answers { nothing }
        presenter.addToExpression(1)
        presenter.addToExpression(Operator.Plus)
        presenter.addToExpression(2)

        // when
        presenter.calculate()

        // then
        verify { view.showResult(3) }
    }
}
