package edu.nextstep.camp.calculator.domain

class CalculateHistory {
    private val _calculateResults = mutableListOf<CalculateResult>()
    val calculateResults: List<CalculateResult>
        get() = _calculateResults.toList()

    fun putCalculateResults(calculateResults: List<CalculateResult>) {
        _calculateResults.clear()
        _calculateResults.addAll(calculateResults)
    }
}
