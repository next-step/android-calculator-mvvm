package edu.nextstep.camp.counter

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CounterViewModel::class.java -> createCounterViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun createCounterViewModel(): CounterViewModel {
        val initialCounter = 0
        return CounterViewModel(initialCounter)
    }
}