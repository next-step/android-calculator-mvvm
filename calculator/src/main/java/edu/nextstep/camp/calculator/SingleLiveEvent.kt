package edu.nextstep.camp.calculator

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val pending = AtomicBoolean(false)

    // 하나의 옵저빙하는 곳만 발행이 된다.
    // set이 되었을 때만 발행이 된다. 화면 회전할 때는 get이라 다시 발행되지 않는다.
    override fun observe(owner: LifecycleOwner, observer: Observer<in T?>) {
        super.observe(owner) { value ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(value)
            }
        }
    }

    @MainThread
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        value = null
    }
}
