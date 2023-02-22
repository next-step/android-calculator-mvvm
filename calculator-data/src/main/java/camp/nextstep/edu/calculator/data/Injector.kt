package camp.nextstep.edu.calculator.data

import android.content.Context
import camp.nextstep.edu.calculator.data.db.Database
import camp.nextstep.edu.calculator.data.repository.ResultRepositoryImpl
import camp.nextstep.edu.calculator.domain.usecase.GetAllResultsUseCase
import camp.nextstep.edu.calculator.domain.usecase.SaveResultUseCase


object Injector {

    fun provideDependenciesForCalculateViewModel(
        applicationContext: Context
    ): Pair<SaveResultUseCase, GetAllResultsUseCase> {
        val db = Database.getDatabase(applicationContext)
        val repo = ResultRepositoryImpl(db)
        return SaveResultUseCase(repo) to GetAllResultsUseCase(repo)
    }
}
