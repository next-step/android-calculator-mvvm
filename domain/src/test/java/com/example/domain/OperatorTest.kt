package com.example.domain

import org.junit.Assert.*
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Enclosed::class)
class TestClass {

    @RunWith(value = Parameterized::class)
    class OperatorTest(private val prime: String, private val operator: Operator) {


        @Test
        fun getEnumTest() {
            assertEquals(operator, Operator.getByPrime(prime))
        }

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "{0}_기호로_{1}_을_받아올_수_있다")
            fun primes(): List<Array<Any>> {
                return listOf(
                    arrayOf("+", Operator.ADD),
                    arrayOf("-", Operator.SUBTRACT),
                    arrayOf("*", Operator.MULTIPLY),
                    arrayOf("/", Operator.DIVIDE),
                )
            }
        }
    }

    class NotParameterizedPart {
        @Test
        fun `덧셈_1_더하기_1은_2`() {
            val result = Operator.ADD.execute(1, 1)
            assertEquals(2, result)
        }

        @Test
        fun `뺄셈_1_빼기_1은_0`() {
            val result = Operator.SUBTRACT.execute(1, 1)
            assertEquals(0, result)
        }

        @Test
        fun `곱셈_3_곱하기_3은_9`() {
            val result = Operator.MULTIPLY.execute(3, 3)
            assertEquals(9, result)
        }

        @Test
        fun `나눗셈_1_나누기_1은_1`() {
            val result = Operator.DIVIDE.execute(1, 1)
            assertEquals(1, result)
        }

        @Test
        fun `나눗셈_소수점_이하는_버린다`() {
            val result = Operator.DIVIDE.execute(8, 3)
            assertEquals(2, result)
        }

        @Test
        fun `나눗셈_0_으로_나누면_ArithmeticException_을_던진다`() {
            val exception = assertThrows(ArithmeticException::class.java) {
                Operator.DIVIDE.execute(4, 0)
            }
            assertEquals("/ by zero", exception.message)
        }

        @Test
        fun `사칙연산_기호가_아닌_문자를_입력하면_IllegalArgumentException_throw`() {
            val exception = assertThrows(IllegalArgumentException::class.java) {
                Operator.getByPrime("^")
            }
            assertEquals("구현되지 않은 기호입니다.", exception.message)
        }
    }
}

