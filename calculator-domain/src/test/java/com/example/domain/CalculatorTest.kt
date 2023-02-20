package com.example.domain

import com.example.domain.models.Calculator
import com.example.domain.models.History
import com.example.domain.repositories.HistoryRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculatorTest {

    private val historyRepository: HistoryRepository = mockk(relaxed = true)
    private val calculator = Calculator(historyRepository = historyRepository)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    @Test
    fun `계산에_성공하면_레포지터리에_계산_결과가_기록된다`() = testScope.runTest {
        // Given
        verify(exactly = 0) { runBlocking { historyRepository.saveHistory(any()) } }
        val rawStatement = "1 + 1"

        // When
        val result: Int = calculator.calculate(rawStatement)
        every { historyRepository.getHistories() } returns flow {
            emit(
                listOf(
                    History(
                        rawStatement,
                        result
                    )
                )
            )
        }

        // Then
        verify(exactly = 1) { runBlocking { historyRepository.saveHistory(any()) } }
        assertTrue(
            historyRepository.getHistories().first() == listOf(
                History(
                    rawStatement,
                    result
                )
            )
        )
    }

    @Test
    fun `덧셈_1_더하기_1은_2`() = testScope.runTest {
        val result: Int = calculator.calculate("1 + 1")
        assertEquals(2, result)
    }

    @Test
    fun `뺄셈_1_빼기_1은_0`() = testScope.runTest {
        val result: Int = calculator.calculate("1 - 1")
        assertEquals(0, result)
    }

    @Test
    fun `곱셈_1_곱하기_1은_1`() = testScope.runTest {
        val result: Int = calculator.calculate("1 * 1")
        assertEquals(1, result)
    }

    @Test
    fun `나눗셈_1_나누기_1은_1`() = testScope.runTest {
        val result: Int = calculator.calculate("1 / 1")
        assertEquals(1, result)
    }

    @Test
    fun `2_더하기_3_곱하기_4_나누기_2는_10`() = testScope.runTest {
        val result: Int = calculator.calculate("2 + 3 * 4 / 2")
        assertEquals(10, result)
    }

    @Test
    fun `0으로_나누면_에러를_던진다`() = testScope.runTest {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            runBlocking {
                calculator.calculate("1 / 0")
            }
        }

        assertEquals("0으로 나눌 수 없습니다.", exception.message)
    }

    @Test
    fun `사칙연산_기호가_아닌_문자를_입력하면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            runBlocking {
                calculator.calculate("1 ^ 0")
            }
        }
        assertEquals("구현되지 않은 기호입니다.", exception.message)
    }

    @Test
    fun `입력값이_빈_문자열_이면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            runBlocking {
                calculator.calculate(" ")
            }
        }
        assertEquals("빈 공백 혹은 문자열은 입력하실 수 없습니다", exception.message)
    }

    @Test
    fun `입력값이_빈_공백_이면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            runBlocking {
                calculator.calculate(" ")
            }
        }
        assertEquals("빈 공백 혹은 문자열은 입력하실 수 없습니다", exception.message)
    }

    @Test
    fun `항의_수가_짝수이면_완성되지_않은_수식이므로_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            runBlocking {
                calculator.calculate("2 +")
            }
        }
        assertEquals("완성되지 않은 수식입니다.", exception.message)
    }

    @Test
    fun `숫자를_연속으로_넣으면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            runBlocking {
                calculator.calculate("2 2 2")
            }
        }
        assertEquals("구현되지 않은 기호입니다.", exception.message)
    }

    @Test
    fun `기호를_연속으로_넣으면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            runBlocking {
                calculator.calculate("2 + +")
            }
        }
        assertEquals("숫자로 변환 불가능한 문자입니다.", exception.message)
    }
}
