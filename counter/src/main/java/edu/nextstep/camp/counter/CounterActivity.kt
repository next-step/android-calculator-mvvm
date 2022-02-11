package edu.nextstep.camp.counter

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding
    private val viewModel: CounterViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return when (modelClass) {
                CounterViewModel::class.java -> createCounterViewModel()
                else -> throw IllegalArgumentException()
            } as T
        }

        private fun createCounterViewModel(): CounterViewModel {
            val testDependencyInitValue = 0
            return CounterViewModel(testDependencyInitValue)
        }
    }
}
