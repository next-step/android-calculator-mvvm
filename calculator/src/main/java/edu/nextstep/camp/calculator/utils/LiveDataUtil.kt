package edu.nextstep.camp.calculator.utils

import androidx.lifecycle.MutableLiveData

class NonNullLiveData<T : Any>(value: T) : MutableLiveData<T>(value) {
    override fun getValue(): T {
        return super.getValue() as T
    }
}
