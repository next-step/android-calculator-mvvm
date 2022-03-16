package edu.nextstep.camp.calculator

import android.app.Instrumentation
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator
import io.mockk.mockk
import nextstep.edu.data.CalculationHistoryRepository
import nextstep.edu.data.History
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class CalculatorViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CalculatorViewModel
    private lateinit var calculationHistoryRepository: CalculationHistoryRepository

    @Before
    fun setUp() {
        calculationHistoryRepository = mockk(relaxed = true)
        viewModel = CalculatorViewModel(Calculator(), calculationHistoryRepository)
    }

    @Test
    fun `계산기 텍스트뷰에 수식이 없을때, 피연산자 3,2,1을 연속해서 추가하면 텍스트뷰에 '321' 이 출력되어야 한다`() {
        // when : 피연산자 3,2,1 추가하면
        viewModel.addToExpression(3)
        viewModel.addToExpression(2)
        viewModel.addToExpression(1)

        // then : 321 출력되어야 한다.
        val expression = Expression(listOf(321))
        assertThat(viewModel.expression.value.toString())
            .isEqualTo(expression.toString())
    }

    @Test
    fun `계산기 텍스트뷰에 수식이 없을때, 연산자를 추가해도 아무런 변화가 없어야 한다`() {
        // when : 연산자 '/' 를 추가하면
        viewModel.addToExpression(Operator.Divide)

        // then : 변화가 없어야 한다.
        val expression = Expression()
        assertThat(viewModel.expression.value.toString())
            .isEqualTo(expression.toString())
    }

    @Test
    fun `계산기 텍스트뷰에 피연산자가 있을때, 연산자를 누르면 피연산자와 연산자가 이어서 추가되어야 한다`() {
        // given : 수식 '6'이 존재할때
        viewModel.addToExpression(6)

        // when : 연산자 '+'를 누르면
        viewModel.addToExpression(Operator.Plus)

        // then : '6+'이 출력되어야 한다.
        val expression = Expression(listOf(6, Operator.Plus))
        assertThat(viewModel.expression.value.toString())
            .isEqualTo(expression.toString())
    }

    @Test
    fun `계산기 텍스트뷰 수식의 마지막이 연산자로 있을때, 연산자를 누르면 연산자가 변경되어야 한다`() {
        // given : 수식이 '9+'로 존재할때
        viewModel.addToExpression(9)
        viewModel.addToExpression(Operator.Plus)

        // when : 연산자 '/'를 추가하면
        viewModel.addToExpression(Operator.Divide)

        // then : 연산자 '9/'이 출력되어야 한다.
        val expression = Expression(listOf(9, Operator.Divide))
        assertThat(viewModel.expression.value.toString())
            .isEqualTo(expression.toString())
    }

    @Test
    fun `계산기 텍스트뷰에 수식이 있을때, 지우기 버튼을 수식의 마지막 문자가 지워진다`() {
        // given : 수식 '20/10'이 있을때
        viewModel.addToExpression(20)
        viewModel.addToExpression(Operator.Divide)
        viewModel.addToExpression(10)

        // when : 지우기 버튼을 누르면
        viewModel.removeLast()

        // then : '20/1' 이 출력되어야 한다.
        val expression = Expression(listOf(20, Operator.Divide, 1))
        assertThat(viewModel.expression.value.toString())
            .isEqualTo(expression.toString())
    }

    @Test
    fun `계산기 텍스트뷰에 완전한 수식이 있을때, = 버튼을 누르면 수식이 계산되어야 한다`() {
        // given : 완전한 수식 '3+10'이 있을때
        viewModel.addToExpression(3)
        viewModel.addToExpression(Operator.Plus)
        viewModel.addToExpression(10)

        // when : '=' 버튼을 누르면
        viewModel.calculate()

        // then : 계산 결과 '13'이 출력 되어야 한다.
        val expression = Expression(listOf(13))
        assertThat(viewModel.expression.value.toString())
            .isEqualTo(expression.toString())
    }

    @Test
    fun `계산기 텍스트뷰에 불완전한 수식이 있을때, = 버튼을 누르면 "완성되지 않은 수식입니다"라는 토스트가 출력되어야 한다`() {
        // given : 불완전한 수식 '12*'이 있을때
        viewModel.addToExpression(12)
        viewModel.addToExpression(Operator.Multiply)

        // when : '=' 버튼을 누르면
        viewModel.calculate()

        // then : "완성되지 않은 수식입니다"라는 토스트가 출력 되어야 한다.
        assertThat(viewModel.errorToast.value)
            .isEqualTo(true)
    }

    @Test
    fun `계산기 텍스트뷰에 완전한 수식이 있을때, = 버튼을 누르면 계산 기록이 저장되어야 한다`() {
        // given : 완전한 수식 '18*10'이 있을때
        viewModel.addToExpression(18)
        viewModel.addToExpression(Operator.Multiply)
        viewModel.addToExpression(10)

        // when : '=' 버튼을 누르면
        viewModel.calculate()

        // then : 계산기록이 저장 되어야 한다.
        val history = History("18*10", 180)

        assertThat(viewModel.historyDataList.getOrAwaitValue()).isEqualTo(history)
    }
}