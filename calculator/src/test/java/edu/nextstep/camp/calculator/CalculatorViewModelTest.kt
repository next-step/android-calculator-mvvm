package edu.nextstep.camp.calculator

import com.google.common.truth.Truth.assertThat
import edu.nextstep.camp.calculator.data.datasource.local.ExpressionHistoryLocalDataSource
import edu.nextstep.camp.calculator.data.repository.ExpressionHistoryRepositoryImpl
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.model.ExpressionHistory
import edu.nextstep.camp.calculator.domain.repository.ExpressionHistoryRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)

class CalculatorViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var calculatorVM: CalculatorViewModel
    private lateinit var expressionHistoryRepository: ExpressionHistoryRepository

    @Before
    fun setUp() {
        val expressionHistoryLocalDataSource: ExpressionHistoryLocalDataSource = mockk()
        expressionHistoryRepository = ExpressionHistoryRepositoryImpl(
            ioDispatcher = UnconfinedTestDispatcher(mainDispatcherRule.testDispatcher.scheduler),
            expressionHistoryLocalDataSource = expressionHistoryLocalDataSource
        )
    }

    @Test
    fun 숫자가_입력되면_수식에_추가되고_변경된_수식을_보여줘야_한다() = runTest {
        // given
        calculatorVM =
            CalculatorViewModel(expressionHistoryRepository = expressionHistoryRepository)
        // when
        calculatorVM.addToExpression(1)
        advanceUntilIdle()
        // then
        val expectedExpression = Expression(listOf(1))
        val actual = calculatorVM.expression.first()
        assertThat(actual).isEqualTo(expectedExpression)
    }

    @Test
    fun 연산자가_입력되면_수식에_추가되고_변경된_수식을_보여줘야_한다() = runTest {
        // given
        calculatorVM = CalculatorViewModel(
            initialExpression = Expression(listOf(1)),
            expressionHistoryRepository = expressionHistoryRepository
        )
        // when
        calculatorVM.addToExpression(Operator.Plus)
        advanceUntilIdle()
        // then
        val expectedExpression = Expression(listOf(1, Operator.Plus))
        val actual = calculatorVM.expression.first()
        assertThat(actual).isEqualTo(expectedExpression)
    }

    @Test
    fun 지우기가_실행되면_수식의_마지막이_지워지고_변경된_수식을_보여줘야_한다() = runTest {
        // given
        calculatorVM = CalculatorViewModel(
            initialExpression = Expression(listOf(1)),
            expressionHistoryRepository = expressionHistoryRepository
        )
        // when
        calculatorVM.removeLast()
        advanceUntilIdle()
        // then
        val expectedExpression = Expression()
        val actual = calculatorVM.expression.first()
        assertThat(actual).isEqualTo(expectedExpression)
    }

    @Test
    fun 계산이_실행되면_계산기에_의해_계산되고_결과를_화면에_보여줘야_한다() = runTest {
        // given
        calculatorVM =
            CalculatorViewModel(
                initialExpression = Expression(listOf(1, Operator.Plus, 2)),
                expressionHistoryRepository = expressionHistoryRepository
            )
        // when
        calculatorVM.calculate()
        advanceUntilIdle()
        // then
        val expectedExpression = Expression(listOf(3))
        val actual = calculatorVM.expression.first()
        assertThat(actual).isEqualTo(expectedExpression)
    }

    @Test
    fun 계산이_실행되면_계산기에_의해_계산되고_결과를_기록에_저장한다() = runTest {
        //given
        calculatorVM =
            CalculatorViewModel(
                initialExpression = Expression(listOf(1, Operator.Plus, 2)),
                expressionHistoryRepository = expressionHistoryRepository
            )
        //when
        calculatorVM.calculate()
        advanceUntilIdle()
        //then
        coVerify { expressionHistoryRepository.insert(ExpressionHistory("1 + 2", 3)) }
    }

    @Test
    fun 저장한_기록을_모두_가져온다() = runTest(mainDispatcherRule.testDispatcher.scheduler) {
        //given
        calculatorVM =
            CalculatorViewModel(expressionHistoryRepository = expressionHistoryRepository)
        //when
        calculatorVM.getAllExpressionHistoryAndShowHistory()
        advanceUntilIdle()
        //then
        coVerify { expressionHistoryRepository.getAll() }
    }
}
