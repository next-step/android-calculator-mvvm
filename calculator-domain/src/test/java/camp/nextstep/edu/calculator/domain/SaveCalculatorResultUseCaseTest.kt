package camp.nextstep.edu.calculator.domain

import camp.nextstep.edu.calculator.domain.repository.CalculatorResultRepository
import camp.nextstep.edu.calculator.domain.usecase.SaveCalculatorResultUseCase
import io.mockk.*
import org.junit.Before
import org.junit.Test

class SaveCalculatorResultUseCaseTest {
    private lateinit var saveUseCase: SaveCalculatorResultUseCase

    @Before
    fun setUp() {
        val repository = mockk<CalculatorResultRepository>(relaxed = true)
        saveUseCase = SaveCalculatorResultUseCase(repository)
    }

    @Test
    fun `save 함수 호출 테스트`() {
        // when
        every { saveUseCase("1 + 2", 3) } just runs

        saveUseCase("1 + 2", 3)

        //then
        verify { saveUseCase("1 + 2", 3) }
    }
}