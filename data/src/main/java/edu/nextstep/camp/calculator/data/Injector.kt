package edu.nextstep.camp.calculator.data

import android.content.Context
import edu.nextstep.camp.calculator.domain.ExpressionHistoryRepository

object Injector {

    private var appDatabase: AppDatabase? = null
    private var expressionHistoryRepository: ExpressionHistoryRepository? = null

    private fun provideAppDatabase(context: Context): AppDatabase {
        if (appDatabase == null) {
            synchronized(AppDatabase::class) {
                if (appDatabase == null) {
                    appDatabase = AppDatabase.create(context)
                }
            }
        }
        return appDatabase!!
    }

    fun provideExpressionHistoryRepository(context: Context): ExpressionHistoryRepository {
        val appDatabase = provideAppDatabase(context)

        if (expressionHistoryRepository == null) {
            synchronized(AppDatabase::class) {
                if (expressionHistoryRepository == null) {
                    expressionHistoryRepository = RoomExpressionHistoryRepository(appDatabase.expressionHistoryDao())
                }
            }
        }
        return expressionHistoryRepository!!
    }
}