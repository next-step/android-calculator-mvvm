package camp.nextstep.edu.calculator.domain.repo

data class History(
    val expression: String,
    val result: Int
) {
    fun getResultPlusEqual(): String {
        return "= $result"
    }
}
