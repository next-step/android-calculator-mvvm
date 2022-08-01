package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel(countValue: Int = 0) : ViewModel() {
    private val countLiveData = MutableLiveData(countValue)
    val count: LiveData<Int>
        get() = countLiveData

    private val countEventDataSingleLiveData = SingleLiveEvent<EventData<CounterEventData>>()
    val countEventDataLiveData: LiveData<EventData<CounterEventData>>
        get() = countEventDataSingleLiveData

    fun increaseCount() {
        val newValue = (count.value ?: 0) + 1
        countLiveData.value = newValue
    }

    fun decreaseCount() {
        if ((count.value ?: 0) == 0) {
            countEventDataSingleLiveData.value = EventData(CounterEventData.CAN_NOT_DECREASE_DOWN_ZERO)
            return
        }
        countLiveData.value = (count.value ?: 0) - 1
    }
}