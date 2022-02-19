package edu.nextstep.camp.calculator.data

class Memory(
    private val results: MutableList<ResultRecord>
) {
    var isMemoryShow = false

    fun addResult(resultRecord: ResultRecord) {
        results.add(resultRecord)
    }

    fun getResult(): List<ResultRecord> {
        return results.toList()
    }
}
