package com.example.domain

import com.example.domain.models.*
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Test

class StatementTest {

    @Test
    fun `배열을_주입한_뒤_원_배열을_변경해도_영향을_받지_않는다`() {
        // Given
        val items = mutableListOf<OperationTerm>()
        val statement = Statement(items)

        // When
        items.add(Operand(1))

        // Then
        assertEquals(Statement(emptyList()), statement)
        assertEquals("", statement.termsToString())
    }

    @Test
    fun `빈_문자열의_수식이_나온다`() {
        // When
        val statement = Statement()

        // Then
        assertEquals(Statement(emptyList()), statement)
        assertEquals("", statement.termsToString())
    }

    @Test
    fun `""일때_1_버튼을_누르면_"1"_반환한다`() {
        // Given
        val statement = Statement()

        // When
        statement.addTerm(Operand(1))

        // Then
        assertEquals("1", statement.termsToString())
        assertEquals(Statement(listOf(Operand(1))), statement)
    }

    @Test
    fun `"5_+_"일때_1_버튼을_누르면_"5_+_1"_반환한다`() {
        // Given
        val statement = Statement(OperationParser.parse("5 +"))

        // When
        statement.addTerm(Operand(1))

        // Then
        assertEquals("5 + 1", statement.termsToString())
        assertEquals(Statement(listOf(Operand(5), Operator.ADD, Operand(1))), statement)
    }

    @Test
    fun `"8"_일때_9_버튼을_누르면_"89"_반환한다`() {
        val statement = Statement(OperationParser.parse("8"))

        // When
        statement.addTerm(Operand(9))

        // Then
        assertEquals("89", statement.termsToString())
        assertEquals(Statement(listOf(Operand(89))), statement)
    }

    @Test
    fun `""일때_연산자_버튼을_누르면_아무_변화없다`() {
        // Given
        val statement = Statement(emptyList())

        // When
        statement.addTerm(Operator.ADD)

        // Then
        assertEquals("", statement.termsToString())
        assertEquals(Statement(emptyList()), statement)
    }

    @Test
    fun `"1"일때_연선자_버튼을_누르면_"1_+"_반환한다`() {
        // Given
        val statement = Statement(OperationParser.parse("1"))

        // When
        statement.addTerm(Operator.ADD)

        // Then
        assertEquals("1 +", statement.termsToString())
        assertEquals(Statement(OperationParser.parse("1 +")), statement)
    }

    @Test
    fun `"1_+"일때_빼기_연산자_버튼을_누르면_"1_-"_반환한다`() {
        // Given
        val statement = Statement(OperationParser.parse("1"))

        // When
        statement.addTerm(Operator.SUBTRACT)

        // Then
        assertEquals("1 -", statement.termsToString())
        assertEquals(Statement(OperationParser.parse("1 -")), statement)
    }

    @Test
    fun `""일때 지우기_버튼을_누르면_아무변화가_없다`() {
        // Given
        val statement = Statement(emptyList())

        // When
        statement.removeTerm()

        // Then
        assertEquals(Statement(emptyList()), statement)
    }

    @Test
    fun `"32_+"일때_지우기_버튼을_누르면_32가_된다`() {
        // Given
        val statement = Statement(OperationParser.parse("32 +"))

        // When
        statement.removeTerm()

        // Then
        assertEquals(Statement(OperationParser.parse("32")), statement)
        assertEquals("32", statement.termsToString())
    }

    @Test
    fun `두자리_수_이상의_피연산자(32)일_때_지우기_버튼을_누르면_한자릿_수의_숫자(3)가_된다`() {
        // Given
        val statement = Statement(OperationParser.parse("32"))

        // When
        statement.removeTerm()

        // Then
        assertEquals(Statement(OperationParser.parse("3")), statement)
        assertEquals("3", statement.termsToString())
    }

    @Test
    fun `한자리_수의_피연산자(3)일_때_지우기_버튼을_누르면_공백이_된다`() {
        // Given
        val statement = Statement(OperationParser.parse("3"))

        // When
        statement.removeTerm()

        // Then
        assertEquals(Statement(emptyList()), statement)
        assertEquals("", statement.termsToString())
    }
}
