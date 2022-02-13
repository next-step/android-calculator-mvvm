package edu.nextstep.camp.calculator.presenter

import edu.nextstep.camp.calculator.MainContract
import edu.nextstep.camp.calculator.domain.CalculatorRepository
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.model.CalculateResult
import edu.nextstep.camp.calculator.domain.model.RecordStatement

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private val expression = Expression()
    private val calculatorRepository = CalculatorRepository()

    override fun calculate(statement: String) {
        view.showMemory(false)
        runCatching {
            val result = expression.calculatedValue(statement)
            val recordStatement = RecordStatement(
                expression = statement,
                calculateResult = CalculateResult(result)
            )
            view.showExpression(result)
            saveStatement(recordStatement)
        }.onFailure {
            view.showError(it.message.toString())
        }
    }

    override fun appendOperand(statement: String, operand: String) {
        view.showMemory(false)
        val appendedStatement = expression.appendOperand(statement, operand)
        view.showExpression(appendedStatement)
    }

    override fun appendOperator(statement: String, operator: String) {
        view.showMemory(false)
        val appendedStatement = expression.appendOperator(statement, operator)
        view.showExpression(appendedStatement)
    }

    override fun deleteLastElement(statement: String) {
        view.showMemory(false)
        val deletedStatement = expression.deleteLastElement(statement)
        view.showExpression(deletedStatement)
    }

    private fun saveStatement(recordStatement: RecordStatement) {
        calculatorRepository.saveStatement(recordStatement)
        view.notifyRecordStatement(calculatorRepository.getRecordStatement())
    }
}