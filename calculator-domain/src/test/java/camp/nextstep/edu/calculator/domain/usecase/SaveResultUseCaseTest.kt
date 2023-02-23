package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.repository.ResultRepository
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class SaveResultUseCaseTest {

    private lateinit var useCase: SaveResultUseCase

    @Before
    fun setUp() {
        val repository = mockk<ResultRepository>(relaxed = true)
        useCase = SaveResultUseCase(repository)
    }

    @Test
    fun `결과 데이터 저장하기 테스트`() {
        runTest {
            // when
            val expression = Expression(listOf(1, Operator.Plus, 2))
            val answer = 3
            every { useCase(expression, answer) } just runs

            useCase(expression, answer)

            //then
            verify { useCase(expression, answer) }
        }
    }
}
