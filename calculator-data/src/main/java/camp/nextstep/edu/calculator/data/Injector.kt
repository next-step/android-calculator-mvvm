package camp.nextstep.edu.calculator.data

import camp.nextstep.edu.calculator.data.db.CalculatorDatabase
import camp.nextstep.edu.calculator.data.repository.ResultRepositoryImpl
import camp.nextstep.edu.calculator.domain.usecase.GetAllResultsUseCase
import camp.nextstep.edu.calculator.domain.usecase.SaveResultUseCase


object Injector {

    fun provideDependenciesForCalculateViewModel(
        database: CalculatorDatabase
    ): Pair<SaveResultUseCase, GetAllResultsUseCase> {
        val repo = ResultRepositoryImpl(database)
        return SaveResultUseCase(repo) to GetAllResultsUseCase(repo)
    }
}
