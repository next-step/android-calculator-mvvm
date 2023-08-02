package camp.nextstep.edu.counter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> createMainViewModel()
            else -> throw IllegalArgumentException()
        } as T
    }

    private fun createMainViewModel(): MainViewModel {
        return MainViewModel()
    }
}
