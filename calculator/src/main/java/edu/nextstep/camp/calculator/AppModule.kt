package edu.nextstep.camp.calculator

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.nextstep.camp.data.LogDatabase
import edu.nextstep.camp.domain.Expression
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideLogDatabase(@ApplicationContext context: Context): LogDatabase {
        return LogDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideExpression(): Expression {
        return Expression.EMPTY
    }

    @Provides
    @Singleton
    fun provideLogRepository(db: LogDatabase): LogRepository {
        return LogRepositoryImpl(db.logDao())
    }
}