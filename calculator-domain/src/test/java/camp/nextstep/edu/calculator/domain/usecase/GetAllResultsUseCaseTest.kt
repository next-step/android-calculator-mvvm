package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.model.CalculatorResult
import camp.nextstep.edu.calculator.domain.repository.ResultRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest


class GetAllResultsUseCaseTest {

    private lateinit var useCase: GetAllResultsUseCase

    @Before
    fun setUp() {
        val repository = mockk<ResultRepository>(relaxed = true)
        useCase = GetAllResultsUseCase(repository)
    }

    @Test
    fun `결과 데이터 불러오기 테스트`() {
        runTest {
            // when
            val expected = listOf(CalculatorResult("1 + 2", "3"))
            every { useCase() } returns flow { expected }

            useCase()
                .collect { actual ->
                    //then
                    assertThat(actual).isEqualTo(expected)
                    verify { useCase() }
                }
        }
    }
}
