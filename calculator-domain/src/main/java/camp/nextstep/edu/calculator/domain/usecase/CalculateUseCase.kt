package camp.nextstep.edu.calculator.domain.usecase

import camp.nextstep.edu.calculator.domain.Calculator

class CalculateUseCase {
    operator fun invoke(expression: String): String? {
        return Calculator().calculate(expression)?.toString()
    }
}
