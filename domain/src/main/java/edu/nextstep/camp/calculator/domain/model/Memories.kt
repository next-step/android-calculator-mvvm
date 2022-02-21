package edu.nextstep.camp.calculator.domain.model

data class Memories(val items: List<Memory> = emptyList()) {

    companion object {
        val EMPTY = Memories()
    }
}