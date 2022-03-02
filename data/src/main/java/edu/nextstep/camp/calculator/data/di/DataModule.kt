package edu.nextstep.camp.calculator.data.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.nextstep.camp.calculator.data.MemoryRepositoryImpl
import edu.nextstep.camp.calculator.data.local.MemoryDao
import edu.nextstep.camp.calculator.data.local.MemoryDatabase
import edu.nextstep.camp.calculator.domain.MemoryRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface DataModule {
    @Singleton
    @Binds
    fun bindMemoryRepository(impl: MemoryRepositoryImpl): MemoryRepository

    companion object {
        @Singleton
        @Provides
        fun provideMemoryDatabase(@ApplicationContext context: Context): MemoryDatabase =
            Room.databaseBuilder(
                context,
                MemoryDatabase::class.java,
                "memory.db"
            ).build()

        @Singleton
        @Provides
        fun provideMemoryDao(database: MemoryDatabase): MemoryDao =
            database.getMemoryDao()
    }
}
