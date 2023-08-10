package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

	private val _count: MutableLiveData<Int> = MutableLiveData(0)
	val count: LiveData<Int>
		get() = _count


	private val _countDownFailure: MutableLiveData<Unit> = SingleLiveEvent()
	val countDownFailure: LiveData<Unit>
		get() = _countDownFailure

	fun countUp() {
		val countSnapshot = _count.value ?: return

		_count.value = countSnapshot + 1
	}

	fun countDown() {
		val countSnapshot = _count.value ?: return

		if (countSnapshot <= 0) {
			_countDownFailure.value = Unit
		} else {
			_count.value = countSnapshot - 1
		}
	}
}