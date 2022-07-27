package edu.nextstep.camp.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * CounterActivity에 대한 viewModel
 * Created by jeongjinhong on 2022. 07. 27..
 */
class CounterViewModel: ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    private var _toastEvent = SingleLiveEvent<Any>()
    val toastEvent: LiveData<Any>
        get() = _toastEvent

    fun increment(){
        val originalCount = count.value ?:0
        _count.value = originalCount + 1
    }
    fun decrement(){
        val originalCount = count.value ?:0
        if(originalCount <= 0){
            _toastEvent.call()
        }else{
            _count.value = originalCount - 1
        }

    }

}