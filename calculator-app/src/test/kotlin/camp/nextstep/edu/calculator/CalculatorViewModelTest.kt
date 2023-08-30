package camp.nextstep.edu.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.calculator.domain.CalculatorRepository
import camp.nextstep.edu.calculator.domain.Memory
import camp.nextstep.edu.calculator.domain.Operator
import com.example.calculator.data.CalculatorDao
import com.example.calculator.data.MemoryEntity
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var fakeRepository: CalculatorRepository

    @Before
    fun init() {
        fakeRepository = FakeRepository()
    }

    @Test
    fun `입력된 숫자가 없을 때, 숫자 클릭 시 해당 숫자가 수식 문자열에 추가된다`() {
        // given : 기본 뷰모델 생성
        val viewModel = CalculatorViewModel(calculatorRepository = fakeRepository)

        // when : 숫자 클릭
        viewModel.addToExpression(2)

        // then : 클릭한 숫자가 입력이 된다.
        assertThat(viewModel.formula.value.toString()).isEqualTo("2")
    }

    @Test
    fun `입력된 숫자가 있을 때, 숫자 클릭 시 해당 숫자가 기존 수식 문자열에 추가된다`() {
        // given : 기본 수식이 1인 뷰모델 생성
        val viewModel = CalculatorViewModel(listOf<Any>(1), calculatorRepository = fakeRepository)

        // when : 숫자 클릭
        viewModel.addToExpression(2)

        // then : 기존 수식에 숫자가 추가된다.
        assertThat(viewModel.formula.value.toString()).isEqualTo("12")
    }

    @Test
    fun `입력된 숫자가 없을 때, 연산자 클릭 시 수식에 변화가 일어나지 않는다`() {
        // given : 기본 뷰모델 생성
        val viewModel = CalculatorViewModel(calculatorRepository = fakeRepository)

        // when : 연산자 클릭 ( + )
        viewModel.addToExpression(Operator.Plus)

        // then : 수식 문자열의 공백을 유지한다. (아무런 작업이 이루어지지 않는다.
        assertThat(viewModel.formula.value.toString()).isEqualTo("")
    }

    @Test
    fun `입력된 숫자가 있을 때, 연산자 클릭 시 기존 수식 문자열에 공백과 연산자가 추가된다`() {
        // given : 기본 수식이 3인 뷰모델 생성
        val viewModel = CalculatorViewModel(listOf<Any>(3), calculatorRepository = fakeRepository)

        // when : 연산자 클릭 ( / )
        viewModel.addToExpression(Operator.Divide)

        // then : 기존 수식에 연산자가 추가된다.
        assertThat(viewModel.formula.value.toString()).isEqualTo("3 /")
    }

    @Test
    fun `입력된 수식이 없을 때, 지우기 버튼 클릭 시 아무 변화가 일어나지 않는다`() {
        // given : 기본 뷰모델 생성
        val viewModel = CalculatorViewModel(calculatorRepository = fakeRepository)

        // when : 지우기 버튼 클릭
        viewModel.removeLast()

        // then : 수식 문자열의 공백을 유지한다. (아무런 작업이 이루어지지 않는다.
        assertThat(viewModel.formula.value.toString()).isEqualTo("")
    }

    @Test
    fun `입력된 수식이 있을 때, 지우기 버튼 클릭 시 수식 문자열의 마지막 문자가 삭제된다`() {
        // given : 기본 수식이 33 + 12 인 뷰모델 생성
        val viewModel = CalculatorViewModel(listOf<Any>(33, Operator.Plus, 12), calculatorRepository = fakeRepository)

        // when : 지우기 버튼 클릭
        viewModel.removeLast()

        // then : 수식의 문자열 중 가장 마지막 문자가 삭제된다.
        assertThat(viewModel.formula.value.toString()).isEqualTo("33 + 1")

        // when : 지우기 버튼 클릭
        viewModel.removeLast()

        // then : 수식의 문자열 중 가장 마지막 문자가 삭제된다.
        assertThat(viewModel.formula.value.toString()).isEqualTo("33 +")

        // when : 지우기 버튼 클릭
        viewModel.removeLast()

        // then : 수식의 문자열 중 가장 마지막 문자가 삭제된다.
        assertThat(viewModel.formula.value.toString()).isEqualTo("33")

        // when : 지우기 버튼 클릭
        viewModel.removeLast()

        // then : 수식의 문자열 중 가장 마지막 문자가 삭제된다.
        assertThat(viewModel.formula.value.toString()).isEqualTo("3")
    }

    @Test
    fun `입력된 수신이 완전할 때, 계산 버튼 클릭 시 계산된 결과를 보여준다`() {
        // given : 기본 수식이 33 + 12 인 뷰모델 생성
        val viewModel = CalculatorViewModel(listOf<Any>(33, Operator.Plus, 12), calculatorRepository = fakeRepository)

        // when : 계산 버튼 클릭
        viewModel.calculate()

        // then : 수식을 계산한 결과를 보여준다.
        assertThat(viewModel.formula.value.toString()).isEqualTo("45")
    }

    @Test
    fun `입력된 수식이 완전하지 않을 때, 계산 버튼 클릭 시 에러 토스트 메시지를 보여준다`() {
        // given : 기본 수식이 33 + 12 / 인 뷰모델 생성
        val viewModel = CalculatorViewModel(listOf<Any>(33, Operator.Plus, 12, Operator.Divide), calculatorRepository = fakeRepository)

        // when : 계산 버튼 클릭
        viewModel.calculate()

        // then : 토스트 메시지 "완성되지 않은 수식입니다"를 보여줍니다.
        // R.string.incomplete_expression == 완성되지 않은 수식입니다.
        assertThat(viewModel.toastEvent.value).isEqualTo(R.string.incomplete_expression)
    }

    // calculate()
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `계산을 하면 로컬 DB에 저장되고 불러오기를 할 수 있다`() {
        // given : viewModel을 생성한다.
        val memory = Memory(expression = "33 + 12", result = "45")

        val viewModel = CalculatorViewModel(
            initFormula = listOf<Any>(33, Operator.Plus, 12),
            calculatorRepository = fakeRepository
        )

        // when : 계산을 한다.
        runTest(UnconfinedTestDispatcher()) {
            viewModel.calculate()
        }
        // then : DB에 저장된 값과 계산된 결과의 값이 같다.
        assertThat(fakeRepository.getMemories()[0]).isEqualTo(memory)
    }
}
