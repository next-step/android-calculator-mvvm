package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)

class CalculatorViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var calculatorVM: CalculatorViewModel

    @Test
    fun 숫자가_입력되면_수식에_추가되고_변경된_수식을_보여줘야_한다() = runTest {
        // given
        calculatorVM = CalculatorViewModel()
        // when
        calculatorVM.addToExpression(1)
        advanceUntilIdle()
        // then
        val actual = calculatorVM.expression.first().toString()
        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun 연산자가_입력되면_수식에_추가되고_변경된_수식을_보여줘야_한다() = runTest {
        // given
        calculatorVM = CalculatorViewModel(initialExpression = Expression(listOf(1)))
        // when
        calculatorVM.addToExpression(Operator.Plus)
        advanceUntilIdle()
        // then
        val actual = calculatorVM.expression.first().toString()
        assertThat(actual).isEqualTo("1 +")
    }

    @Test
    fun 지우기가_실행되면_수식의_마지막이_지워지고_변경된_수식을_보여줘야_한다() = runTest {
        // given
        calculatorVM = CalculatorViewModel(initialExpression = Expression(listOf(1)))
        // when
        calculatorVM.removeLast()
        advanceUntilIdle()
        // then
        val actual = calculatorVM.expression.first().toString()
        assertThat(actual).isEqualTo("")
    }

    @Test
    fun 계산이_실행되면_계산기에_의해_계산되고_결과를_화면에_보여줘야_한다() = runTest {
        // given
        calculatorVM = CalculatorViewModel(initialExpression = Expression(listOf(1, Operator.Plus, 2)))
        // when
        calculatorVM.calculate()
        advanceUntilIdle()
        // then
        val actual = calculatorVM.expression.first().toString()
        assertThat(actual).isEqualTo("3")
    }
}
