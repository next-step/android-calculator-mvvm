package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> get() = _counter

    private val _failInfo = SingleLiveEvent<Boolean>()
    val failInfo: LiveData<Boolean> get() = _failInfo

    fun up() {
        _counter.value = _counter.value?.plus(1)
    }

    fun down() {
        runCatching {
            // 기본 값이 0 이므로 ?: 0 처리
            val currentCount = counter.value ?: 0

            if (currentCount.minus(1) < 0) {
                throw IllegalArgumentException("")
            }

            _counter.value = counter.value?.minus(1)
            _counter.value
        }
            .onSuccess {}
            .onFailure { _failInfo.value = true }
    }

}