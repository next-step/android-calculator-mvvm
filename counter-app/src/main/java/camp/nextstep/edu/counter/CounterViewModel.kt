package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

	private val _count: MutableLiveData<Int> = MutableLiveData(0)
	val count: LiveData<Int>
		get() = _count

	fun countUp() {
		val countSnapshot = _count.value ?: return

		_count.value = countSnapshot + 1
	}

	fun countDown(): Boolean {
		val countSnapshot = _count.value ?: return false

		return if (countSnapshot <= 0) {
			false
		} else {
			_count.value = countSnapshot - 1
			true
		}
	}
}