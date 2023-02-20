package com.example.domain

import com.example.domain.models.Operand
import org.junit.Assert.*
import org.junit.Test

class OperandTest {

    @Test
    fun `0_통해_Operand_를_생성할_수_있다`() {
        val operand = Operand(0)

        assertTrue(operand is Operand)
        assertEquals(Operand(0), operand)
    }
    @Test
    fun `양수를_통해_Operand_를_생성할_수_있다`() {
        val operand = Operand(1)

        assertTrue(operand is Operand)
        assertEquals(Operand(1), operand)
    }

    @Test
    fun `양수_문자열을_통해_Operand_를_생성할_수_있다`() {
        val operand = Operand.fromTerm("1")

        assertTrue(operand is Operand)
        assertEquals(Operand(1), operand)
    }

    @Test
    fun `음수를_넣으면_IllegalArgumentException_을_던진다`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Operand(-1)
        }
        assertEquals("음수를 지원 하지 않습니다.", exception.message)
    }

    @Test
    fun `인티저의_범위보다_큰_숫자_문자열_을_넣으면_IllegalArgumentException_을_던진다`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Operand.fromTerm("111111111111111111")
        }
        assertEquals("숫자로 변환 불가능한 문자입니다.", exception.message)
    }

    @Test
    fun `10_미만_이면_false를_반환한다`() {
        val operand = Operand(9)

        assertFalse(operand.isOverUnits())
    }
    @Test
    fun `10_이상_이면_true를_반환한다`() {
        val operand = Operand(10)

        assertTrue(operand.isOverUnits())
    }

    @Test
    fun `한자리수일_때_removeLast_하면_null_을_반환한다`() {
        val operand = Operand(9)

        val lastRemoved = operand.removeLastOrNull()
        assertEquals(null, lastRemoved)
    }

    @Test
    fun `두자리수_이상일_때_removeLast_하면_일의자리를_날린_Operand_객체를_반환한다`() {
        val operand = Operand(10)

        val lastRemoved = operand.removeLastOrNull()
        assertEquals(Operand(1), lastRemoved)
    }
}
