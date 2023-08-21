package camp.nextstep.edu.calculator.di

import android.content.Context
import androidx.room.Room
import camp.nextstep.edu.calculator.data.HistoryDao
import camp.nextstep.edu.calculator.data.HistoryDatabase
import camp.nextstep.edu.calculator.data.HistoryRepositoryImpl
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.repo.HistoryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindRepository(repository: HistoryRepositoryImpl): HistoryRepository

    companion object {
        private const val HISTORY_DB = "HISTORY_DB"

        @Provides
        fun provideCalculatorDatabase(
            @ApplicationContext
            context: Context
        ): HistoryDatabase {
            return Room.databaseBuilder(
                context,
                HistoryDatabase::class.java,
                HISTORY_DB
            ).build()
        }

        @Provides
        fun provideCalculatorDao(
            historyDatabase: HistoryDatabase
        ): HistoryDao {
            return historyDatabase.historyDao()
        }

        @Provides
        fun provideExpression(): Expression {
            return Expression.EMPTY
        }

        @Provides
        fun provideCalculator(): Calculator {
            return Calculator()
        }
    }
}
