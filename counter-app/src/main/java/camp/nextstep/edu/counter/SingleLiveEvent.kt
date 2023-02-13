package camp.nextstep.edu.counter

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {
    /**
     * 멀티 쓰레딩 환경에서 동시성을 보장하는 AtomicBoolean.
     * false 로 초기화 되어있다.
     */
    private val pending = AtomicBoolean(false)

    /**
     * View 가 활성화 상태가 되거나 setValue 로 값이 바뀌었을 때 호출되는 observe 함수
     * pending.compareAndSet(true, false) 라는 것은, 위 pending 변수가 true 이면,
     * if문 내의 로직을 처리하고 false 로 바꾼다는 것이다.
     *
     * 아래의 setValue 를 통해서만 pending 값이 true로 바뀌기 때문에,
     * Configuration Changed 가 일어나도 pending 값은 false 이기 때문에 observe 가 데이터를 전달하지 않는다.
     */
    override fun observe(owner: LifecycleOwner, observer: Observer<in T?>) {
        if(hasActiveObservers()) {
            Log.d("SingleLiveEvent", "Multiple observers registered but only one will be notified of changeds.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner) { value ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(value)
            }
        }
    }

    /**
     * LiveData 로써 가지고 있는 데이터의 값을 변경하는 함수
     * 여기서 pending(AtomicBoolean) 의 변수는 true 로 바꾸어
     * observe 내의 if 문을 처리할 수 있도록 함
     */
    @MainThread
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }

    /**
     * 데이터의 속성을 지정해 주지 않아도 call 만으로 setValue 를 호출 가능하다.
     */
    @MainThread
    fun call() {
        value = null
    }
}
