package camp.nextstep.edu.calculator

import com.example.domain.Operand
import com.example.domain.Operator
import com.example.domain.Statement
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CalculatePresenterTest {
    private lateinit var presenter: CalculateContract.Presenter
    private lateinit var view: CalculateContract.View

    @Before
    fun setUp() {
        view = mockk()
        presenter = CalculatePresenter(view)
    }

    @Test
    fun `숫자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expected = Statement(listOf(Operand(1)))
        every { view.showStatement(expected) } answers { nothing }

        // when
        presenter.addTerm(Operand(1))

        // then
        verify { view.showStatement(expected) }
    }

    @Test
    fun `연산자가 입력되면 수식에 추가되고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<Statement>()
        every { view.showStatement(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addTerm(Operand(1))
        presenter.addTerm(Operator.ADD)

        // then
        val actual = expressionSlot.captured
        assertThat(actual.termsToString()).isEqualTo("1 +")
        verify { view.showStatement(actual) }
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워지고 변경된 수식을 보여줘야 한다`() {
        // given
        val expressionSlot = slot<Statement>()
        every { view.showStatement(capture(expressionSlot)) } answers { nothing }

        // when
        presenter.addTerm(Operand(1))
        presenter.removeTerm()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.termsToString()).isEqualTo("")
        verify { view.showStatement(actual) }
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산되고 결과를 화면에 보여줘야 한다`() {
        // given
        every { view.showStatement(any()) } answers { nothing }
        every { view.showResult(any()) } answers { nothing }
        presenter.addTerm(Operand(1))
        presenter.addTerm(Operator.ADD)
        presenter.addTerm(Operand(2))

        // when
        presenter.calculate()

        // then
        verify { view.showResult(3) }
    }
}