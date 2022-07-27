package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(count: Int = 0) : ViewModel() {

    private val countMutaLiveData = MutableLiveData<Int>()

    val count: LiveData<Int>
        get() = countMutaLiveData

    val errorMessageData = SingleLiveEvent<Event<Unit>>()

    init {
        countMutaLiveData.value = count
    }

    fun incrementCount() {
        val originalCountData = count.value ?: 0
        countMutaLiveData.value = originalCountData + 1
    }

    fun decrementCount() {
        val originalCount = count.value ?: 0
        if (originalCount > 0) {
            countMutaLiveData.value = originalCount - 1
            return
        }
        errorMessageData.call()
    }
}