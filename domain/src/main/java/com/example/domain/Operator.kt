package com.example.domain

enum class Operator(val prime: String) : OperationTerm {
    ADD("+") {
        override fun execute(preOperand: Int, postOperand: Int): Int = preOperand + postOperand
    },
    SUBTRACT("-") {
        override fun execute(preOperand: Int, postOperand: Int): Int = preOperand - postOperand
    },
    MULTIPLY("*") {
        override fun execute(preOperand: Int, postOperand: Int): Int = preOperand * postOperand
    },
    DIVIDE("/") {
        override fun execute(preOperand: Int, postOperand: Int): Int {
            require(postOperand != 0){
                "0으로 나눌 수 없습니다."
            }
            return preOperand / postOperand
        }
    };

    companion object {
        fun getByPrime(prime: String): Operator {
            val oper = values().find { operator ->
                prime == operator.prime
            }
            require(oper != null) {
                "구현되지 않은 기호입니다."
            }
            return oper
        }
    }

    abstract fun execute(preOperand: Int, postOperand: Int): Int
}