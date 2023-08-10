package camp.nextstep.edu.calculator.domain.model

data class History(
    val id: Int = 0,
    val expressions: String,
    val result: Int
)
