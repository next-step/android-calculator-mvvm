package edu.nextstep.camp.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.nextstep.camp.domain.LogRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideLogDatabase(@ApplicationContext context: Context): LogDatabase {
        return LogDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideLogRepository(db: LogDatabase): LogRepository {
        return LogRepositoryImpl(db.logDao())
    }
}