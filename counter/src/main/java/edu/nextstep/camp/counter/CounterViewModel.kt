package edu.nextstep.camp.counter

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    val eventShowToast = SingleLiveEvent<Int>()

    private val _count = MutableLiveData(0)
    val count: LiveData<Int> = _count

    fun countUp() {
        val currentCount = _count.value ?: 0
        _count.value = currentCount + 1
    }

    fun countDown() {
        val currentCount = _count.value ?: 0
        if (currentCount <= 0) {
            showToast(R.string.can_not_lower_below_zero)
            return
        }
        _count.value = currentCount - 1
    }

    private fun showToast(@StringRes stringId: Int) {
        eventShowToast.value = stringId
    }
}
