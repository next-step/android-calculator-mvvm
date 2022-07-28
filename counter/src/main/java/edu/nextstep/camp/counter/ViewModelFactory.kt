package edu.nextstep.camp.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.domain.counter.Counter

class ViewModelFactory(private val counter: Counter = Counter()) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CounterViewModel::class.java -> createCounterViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun createCounterViewModel(): CounterViewModel {
        return CounterViewModel(counter)
    }
}