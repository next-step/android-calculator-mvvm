package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

// https://gist.github.com/LouisCAD/7690950f3a839730d025ca127b46ebde

fun <T> Flow<T>.toLiveData(): LiveData<T> = liveData {
    collect { emit(it) }
}

fun <T> LiveData<T>.asFlow(): Flow<T> = callbackFlow {
    val observer = Observer<T> { value -> this.trySend(value).isSuccess }
    observeForever(observer)
    awaitClose {
        removeObserver(observer)
    }
}.flowOn(Dispatchers.Main.immediate)
