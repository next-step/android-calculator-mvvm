package camp.nextstep.edu.calculator.data.repository

import android.content.Context
import camp.nextstep.edu.calculator.domain.repository.ResultExpressionRepository

object DataInjector {
    fun provideMemoryRepository(context: Context): ResultExpressionRepository {
        return ResultExpressionDbRepository(context)
    }
}
