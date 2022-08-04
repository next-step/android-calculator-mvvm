package edu.nextstep.camp.calculator.data.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object CoroutinesModule {
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
