package com.example.domain.models

@JvmInline
value class Operand(val value: Int) : OperationTerm {
    init {
        require(value >= 0) {
            "음수를 지원 하지 않습니다."
        }
    }

    fun isOverUnits(): Boolean = value >= TENS


    fun removeLastOrNull(): Operand? {
        if (isOverUnits()) return Operand(value / TENS)
        return null
    }

    companion object {
        private const val TENS = 10
        fun fromTerm(term: String): Operand {
            val value = term.toIntOrNull()
            require(value != null) {
                "숫자로 변환 불가능한 문자입니다."
            }
            return Operand(value)
        }
    }

}
