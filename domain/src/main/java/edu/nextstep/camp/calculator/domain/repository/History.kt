package edu.nextstep.camp.calculator.domain.repository

import edu.nextstep.camp.calculator.domain.Expression

data class History(val expression: Expression, val result: Expression)