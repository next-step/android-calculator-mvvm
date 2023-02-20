package camp.nextstep.edu.calculator.data

import camp.nextstep.edu.calculator.data.repository.ResultRepositoryImpl
import camp.nextstep.edu.calculator.domain.usecase.GetAllResultsUseCase
import camp.nextstep.edu.calculator.domain.usecase.SaveResultUseCase


object Injector {

    fun provideDependenciesForCalculateViewModel(): Pair<SaveResultUseCase, GetAllResultsUseCase> {
        val repo = ResultRepositoryImpl
        return SaveResultUseCase(repo) to GetAllResultsUseCase(repo)
    }
}
