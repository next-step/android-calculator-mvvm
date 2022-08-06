package edu.nextstep.camp.calculator.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.nextstep.camp.domain.Expression
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideExpression(): Expression {
        return Expression.EMPTY
    }
}