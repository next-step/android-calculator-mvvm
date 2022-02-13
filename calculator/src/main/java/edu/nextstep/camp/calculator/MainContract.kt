package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.model.RecordStatement

interface MainContract {
    interface View {
        fun showExpression(expression: String?)
        fun showError(errorMessage: String)
        fun notifyRecordStatement(recordStatement: RecordStatement)
        fun showMemory(isVisible: Boolean)
    }

    interface Presenter {
        fun calculate(statement: String)
        fun appendOperand(statement: String, operand: String)
        fun appendOperator(statement: String, operator: String)
        fun deleteLastElement(statement: String)
    }
}