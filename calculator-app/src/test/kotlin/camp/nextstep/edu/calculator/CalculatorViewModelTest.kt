package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.Operator
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `입력된 숫자가 없을 때, 숫자 클릭`() {
        // given : 기본 뷰모델 생성
        val viewModel = CalculatorViewModel()

        // when : 숫자 클릭
        viewModel.addToExpression(2)

        // then : 클릭한 숫자가 입력이 된다.
        assertThat(viewModel.formula.value).isEqualTo("2")
    }

    @Test
    fun `입력된 숫자가 있을 때, 숫자 클릭`() {
        // given : 기본 수식이 1인 뷰모델 생성
        val viewModel = CalculatorViewModel(listOf<Any>(1))

        // when : 숫자 클릭
        viewModel.addToExpression(2)

        // then : 기존 수식에 숫자가 추가된다.
        assertThat(viewModel.formula.value).isEqualTo("12")
    }

    @Test
    fun `입력된 숫자가 없을 때, 연산자 클릭`() {
        // given : 기본 뷰모델 생성
        val viewModel = CalculatorViewModel()

        // when : 연산자 클릭 ( + )
        viewModel.addToExpression(Operator.Plus)

        // then : 수식 문자열의 공백을 유지한다. (아무런 작업이 이루어지지 않는다.
        assertThat(viewModel.formula.value).isEqualTo("")
    }

    @Test
    fun `입력된 숫자가 있을 때, 연산자 클릭`() {
        // given : 기본 수식이 3인 뷰모델 생성
        val viewModel = CalculatorViewModel(listOf<Any>(3))

        // when : 연산자 클릭 ( / )
        viewModel.addToExpression(Operator.Divide)

        // then : 기존 수식에 연산자가 추가된다.
        assertThat(viewModel.formula.value).isEqualTo("3 /")
    }

    @Test
    fun `입력된 수식이 없을 때, 지우기 버튼 클릭`() {
        // given : 기본 뷰모델 생성
        val viewModel = CalculatorViewModel()

        // when : 지우기 버튼 클릭
        viewModel.removeLast()

        // then : 수식 문자열의 공백을 유지한다. (아무런 작업이 이루어지지 않는다.
        assertThat(viewModel.formula.value).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 있을 때, 지우기 버튼 클릭`() {
        // given : 기본 수식이 33 + 12 인 뷰모델 생성
        val viewModel = CalculatorViewModel(listOf<Any>(33, Operator.Plus, 12))

        // when : 지우기 버튼 클릭
        viewModel.removeLast()

        // then : 수식의 문자열 중 가장 마지막 문자가 삭제된다.
        assertThat(viewModel.formula.value).isEqualTo("33 + 1")

        // when : 지우기 버튼 클릭
        viewModel.removeLast()

        // then : 수식의 문자열 중 가장 마지막 문자가 삭제된다.
        assertThat(viewModel.formula.value).isEqualTo("33 +")

        // when : 지우기 버튼 클릭
        viewModel.removeLast()

        // then : 수식의 문자열 중 가장 마지막 문자가 삭제된다.
        assertThat(viewModel.formula.value).isEqualTo("33")

        // when : 지우기 버튼 클릭
        viewModel.removeLast()

        // then : 수식의 문자열 중 가장 마지막 문자가 삭제된다.
        assertThat(viewModel.formula.value).isEqualTo("3")
    }

    @Test
    fun `입력된 수신이 완전할 때, 계산 버튼 클릭`() {
        // given : 기본 수식이 33 + 12 인 뷰모델 생성
        val viewModel = CalculatorViewModel(listOf<Any>(33, Operator.Plus, 12))

        // when : 계산 버튼 클릭
        viewModel.calculate()

        // then : 수식을 계산한 결과를 보여준다.
        assertThat(viewModel.formula.value).isEqualTo("45")
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, 계산 버튼 클릭`() {
        // given : 기본 수식이 33 + 12 / 인 뷰모델 생성
        val viewModel = CalculatorViewModel(listOf<Any>(33, Operator.Plus, 12, Operator.Divide))

        // when : 계산 버튼 클릭
        viewModel.calculate()

        // then : 토스트 메시지 "완성되지 않은 수식입니다"를 보여줍니다.
        assertThat(viewModel.toastEvent.value).isEqualTo("완성되지 않은 수식입니다")
    }
}
