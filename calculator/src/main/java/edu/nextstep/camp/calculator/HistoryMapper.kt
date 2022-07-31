package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.History

internal fun History.toItem() = HistoryItem(
    expression = expression,
    result = "= $result"
)
