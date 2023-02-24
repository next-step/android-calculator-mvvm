package camp.nextstep.edu.calculator

import android.content.Context
import com.example.calculator_data.DaoModule
import com.example.calculator_data.DatabaseModule
import com.example.calculator_data.RepositoryModule
import com.example.calculator_data.database.AppDatabase
import com.example.calculator_data.database.HistoryDao
import com.example.domain.ModelModule
import com.example.domain.UseCaseModule
import com.example.domain.repositories.HistoryRepository

object Injector {
    private fun provideDatabase(context: Context) = DatabaseModule.provideDatabase(context)
    private fun provideDao(context: Context, database: AppDatabase = provideDatabase(context)) =
        DaoModule.provideHistoryDao(database)

    private fun provideRepository(
        context: Context,
        database: AppDatabase = provideDatabase(context),
        dao: HistoryDao = provideDao(context, database)
    ) = RepositoryModule.providerHistoryRepository(dao)

    fun provideCalculator(
        context: Context,
        database: AppDatabase = provideDatabase(context),
        dao: HistoryDao = provideDao(context, database),
        repository: HistoryRepository = provideRepository(
            context,
            database,
            dao
        )
    ) = ModelModule.provideCalculator(repository)

    fun providerGetHistoriesUseCase(
        context: Context,
        database: AppDatabase = provideDatabase(context),
        dao: HistoryDao = provideDao(context, database),
        repository: HistoryRepository = provideRepository(
            context,
            database,
            dao
        )
    ) = UseCaseModule.provideGetHistoriesUseCase(repository)
}
