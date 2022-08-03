package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth
import edu.nextstep.camp.calculator.domain.CalculatorRepository
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 07. 30..
 */
class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    /*
    <공부용 주석 : instantExecutorRule>
     - LiveData를 이용한다면 필수적인느낌, 구성요소 관련작업들을 모두 한 스레드에서 실행되게 함(즉 모든 작업이 동기적으로)
     - LiveData.setValue()만 사용하더라도 MainThread환경이 아니라서 에러남, 그래서 이거 InstantTaskExecutorRule 사용
          -> 물론 사용해도 MainThread환경은 아니지만 사용하면 isMainThread()가 true로 하드코딩되어있음
     */


    private lateinit var viewModel: CalculatorViewModel
    private lateinit var repository: CalculatorRepository

    @Before
    fun setUp() {
        repository = mockk()
        viewModel = CalculatorViewModel()
    }

    @Test
    fun `숫자가 입력되면 수식에 추가된다`() {
        // given
        val expression = Expression(listOf(1))
        viewModel = CalculatorViewModel(_expression = MutableLiveData(expression))

        // when
        viewModel.addToExpression(1)

        // then
        Truth.assertThat(viewModel.expression.value.toString()).isEqualTo("11")
    }

    @Test
    fun `연산자가 입력되면 수식에 추가된다`() {
        // given
        val expression = Expression(listOf(1))
        viewModel = CalculatorViewModel(_expression = MutableLiveData(expression))

        // when
        viewModel.addToExpression(Operator.Plus)

        // then
        Truth.assertThat(viewModel.expression.value.toString()).isEqualTo("1 +")
    }

    @Test
    fun `지우기가 실행되면 수식의 마지막이 지워진다`() {
        // given
        val expression = Expression(listOf(1))
        viewModel = CalculatorViewModel(_expression = MutableLiveData(expression))

        // when
        viewModel.removeLast()

        // then
        Truth.assertThat(viewModel.expression.value.toString()).isEqualTo("")
    }

    @Test
    fun `계산이 실행되면 계산기에 의해 계산된다`() {
        // given
        val expression = Expression(listOf(1, Operator.Plus, 2))
        viewModel = CalculatorViewModel(
            _expression = MutableLiveData(expression),
            calculatorRepository = repository
        )
        every { repository.storeCalculationMemory("1 + 2", 3) } just Runs

        // when
        viewModel.calculate()

        // then
        Truth.assertThat(viewModel.expression.value.toString()).isEqualTo("3")
    }

    @Test
    fun `수식이 완성되지 않았는데 계산이 실행되면 '미완성 수식 에러' 이벤트가 실행된다`() {
        // given
        val expression = Expression(listOf(1, Operator.Plus))
        viewModel = CalculatorViewModel(_expression = MutableLiveData(expression))
        // when
        viewModel.calculate()

        // then
        Truth.assertThat(viewModel.errorEvent.value?.consume())
            .isEqualTo(CalculatorViewModel.ErrorEvent.INCOMPLETE_EXPRESSION_ERROR)
    }

    @Test
    fun `계산기록이 보일때 계산기록 기능 실행하면 사라진다`() {
        // given
        viewModel.updateCalculationMemory()

        // when
        viewModel.updateCalculationMemory()

        // then
        Truth.assertThat(viewModel.toggleCalculationMemory.value)
            .isEqualTo(false)
    }

    @Test
    fun `계산기록이 안보일때 계산기록 기능 실행하면 보인다`() {
        // given
        // when
        viewModel.updateCalculationMemory()

        //then
        Truth.assertThat(viewModel.toggleCalculationMemory.value)
            .isEqualTo(true)
    }

}