package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by link.js on 2022. 07. 26..
 */
class MainViewModel : ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    val toastEvent = SingleLiveEvent<Event<Unit>>()

    fun increment() {
        val originalCount = count.value ?: 0
        _count.value = originalCount + 1
    }

    fun decrement() {
        val originalCount = count.value ?: 0
        if (originalCount > 0) {
            _count.value = originalCount - 1
        } else {
            toastEvent.call()
        }
    }
}