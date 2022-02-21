package edu.nextstep.camp.domain.calculator

class CalculatorMemory(
    initialRecords: List<Record> = emptyList()
) {

    private val _records = initialRecords.toMutableList()
    val records get() = _records.toList()

    fun addRecord(expression: Expression, result: Int) {
        val record = Record(expression, result)
        _records.add(record)
    }

    data class Record(
        val expression: Expression,
        val result: Int
    )
}
