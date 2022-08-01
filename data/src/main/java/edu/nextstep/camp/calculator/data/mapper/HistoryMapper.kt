package edu.nextstep.camp.calculator.data.mapper

import edu.nextstep.camp.calculator.data.model.HistoryData
import edu.nextstep.camp.calculator.domain.History

internal fun History.toData() = HistoryData(expression, result)

internal fun HistoryData.toDomain() = History(expression, result)
