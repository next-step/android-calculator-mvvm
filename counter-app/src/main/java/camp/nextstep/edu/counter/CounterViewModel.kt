/**
 * @author Daewon on 24,August,2023
 *
 */

package camp.nextstep.edu.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>().apply { value = 0 }
    val count: LiveData<Int>
        get() = _count

    private val _uiEffect = SingleLiveEvent<UiEffect>()
    val uiEffect: LiveData<UiEffect>
        get() = _uiEffect

    fun increase() {
        _count.value = _count.value?.plus(1)
    }

    fun decrease() {
        if (count.value!! <= 0) {
            _uiEffect.value = UiEffect.Error("0 이하로 내릴 수 없습니다.")
        } else {
            _count.value = _count.value?.minus(1)
        }
    }
}

sealed interface UiEffect {
    data class Error(val message: String) : UiEffect
}
