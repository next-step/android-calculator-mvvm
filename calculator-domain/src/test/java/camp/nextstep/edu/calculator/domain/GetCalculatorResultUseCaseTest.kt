package camp.nextstep.edu.calculator.domain

import camp.nextstep.edu.calculator.domain.model.CalculatorResultData
import camp.nextstep.edu.calculator.domain.repository.CalculatorResultRepository
import camp.nextstep.edu.calculator.domain.usecase.GetCalculatorResultUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class GetCalculatorResultUseCaseTest {
    private lateinit var getUseCase: GetCalculatorResultUseCase
    private lateinit var repository : CalculatorResultRepository

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        getUseCase = GetCalculatorResultUseCase(repository)
    }

    @Test
    fun `load 함수 호출 테스트`() {
        // when
        every { repository.getAllResult() } returns listOf(CalculatorResultData("1 + 2", 3))

        val result = repository.getAllResult()
        val actual = getUseCase()
        //then
        assertThat(actual).isEqualTo(result)
        verify { getUseCase() }
    }
}