package com.example.domain

import com.example.domain.models.Operand
import com.example.domain.models.OperationParser
import com.example.domain.models.Operator
import org.junit.Assert.*
import org.junit.Test

fun String.toOperator(): Operator = Operator.getByPrime(this)

fun Int.toOperand(): Operand = Operand(this)


class OperationParserTest {

    @Test
    fun `짝수번째_문자는_Operand_로_생성된다`() {
        val result = OperationParser.parse("2 + 3 * 4 / 2")
        for (index: Int in result.indices step 2) {
            assertTrue(result[index] is Operand)
        }
    }

    @Test
    fun `홀수번째_문자는_Operator_로_생성된다`() {
        val result = OperationParser.parse("2 + 3 * 4 / 2")
        for (index: Int in 1 until result.size step 2) {
            assertTrue(result[index] is Operator)
        }
    }

    @Test
    fun `입력값이_빈_문자열_이면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            OperationParser.parse(" ")
        }
        assertEquals("빈 공백 혹은 문자열은 입력하실 수 없습니다", exception.message)
    }

    @Test
    fun `입력값이_빈_공백_이면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            OperationParser.parse(" ")
        }
        assertEquals("빈 공백 혹은 문자열은 입력하실 수 없습니다", exception.message)
    }

    @Test
    fun `숫자를_연속으로_넣으면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            OperationParser.parse("2 2 2")
        }
        assertEquals("구현되지 않은 기호입니다.", exception.message)
    }

    @Test
    fun `기호를_연속으로_넣으면_IllegalArgumentException_throw`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            OperationParser.parse("2 + +")
        }
        assertEquals("숫자로 변환 불가능한 문자입니다.", exception.message)
    }

    @Test
    fun `2 더하기 3 곱하기 4 나누기 2 파싱`() {
        val result = OperationParser.parse("2 + 3 * 4 / 2")

        assertEquals(
            listOf(
                2.toOperand(),
                "+".toOperator(),
                3.toOperand(),
                "*".toOperator(),
                4.toOperand(),
                "/".toOperator(),
                2.toOperand()
            ),
            result
        )
    }
}
