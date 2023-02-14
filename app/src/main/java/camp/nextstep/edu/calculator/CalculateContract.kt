package camp.nextstep.edu.calculator

import com.example.domain.OperationTerm
import com.example.domain.Statement

interface CalculateContract {
    interface View {
        var presenter: Presenter

        fun showStatement(statement: Statement)

        fun showResult(result: Int)

        fun showCalculateError(error: Throwable)
    }

    interface Presenter {
        fun addTerm(term: OperationTerm)

        fun removeTerm()

        fun calculate()
    }
}