package com.example.domain.models

class Statement(terms: List<OperationTerm> = emptyList()) {

    private var terms: MutableList<OperationTerm>

    init {
        this.terms = terms.toMutableList()
    }

    fun addTerm(term: OperationTerm) {
        when (val last = terms.lastOrNull()) {
            is Operator -> {
                addTermWhenLastIsOperator(term, last)
            }
            is Operand -> {
                addTermWhenLastIsOperand(term, last)
            }
            null -> {
                addTermWhenEmpty(term)
            }
        }
    }

    private fun addTermWhenEmpty(term: OperationTerm) {
        if (term is Operand) {
            terms.add(term)
        }
    }

    private fun addTermWhenLastIsOperand(term: OperationTerm, last: Operand) {
        when (term) {
            is Operand -> {
                terms.removeLast()
                terms.add(Operand.fromTerm("${last.value}${term.value}"))
            }
            is Operator -> {
                terms.add(term)
            }
        }
    }

    private fun addTermWhenLastIsOperator(term: OperationTerm, last: Operator) {
        if (term is Operator) {
            terms.removeLast()
        }
        terms.add(term)
    }


    fun removeTerm() {
        when (val last = terms.lastOrNull()) {
            is Operator -> {
                terms.removeLast()
            }
            is Operand -> {
                removeTermWhenLastIsOperand(last)
            }
        }
    }

    private fun removeTermWhenLastIsOperand(last: Operand) {
        terms.removeLast()
        val lastRemoved = last.removeLastOrNull()
        if (lastRemoved != null) {
            terms.add(lastRemoved)
        }
    }

    fun termsToString(): String {
        return if (terms.isEmpty()) "" else fold()
    }

    private fun fold(): String {
        return terms.fold("") { acc: String, term: OperationTerm ->
            if (term is Operator) "$acc ${term.prime}" else "$acc ${(term as Operand).value}"
        }.trim()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Statement

        if (terms != other.terms) return false

        return true
    }

    override fun hashCode(): Int {
        return terms.hashCode()
    }
}
