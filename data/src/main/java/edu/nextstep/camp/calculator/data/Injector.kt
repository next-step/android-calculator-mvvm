package edu.nextstep.camp.calculator.data

import android.content.Context
import edu.nextstep.camp.calculator.domain.ExpressionHistoryRepository

object Injector {

    private fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.create(context)
    }

    fun provideExpressionHistoryRepository(context: Context): ExpressionHistoryRepository {
        val appDatabase = provideAppDatabase(context)
        return RoomExpressionHistoryRepository(appDatabase.expressionHistoryDao())
    }
}