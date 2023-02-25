package com.example.calculator_data

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.example.calculator_data.database.AppDatabase

internal object DatabaseModule {
    fun provideDatabase(
        context: Context
    ): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "history").build()

    fun providerInMemoryDatabase(
        context: Context
    ): AppDatabase =
        Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
}
