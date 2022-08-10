package edu.nextstep.camp.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding
import edu.nextstep.camp.domain.counter.Counter

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter)

        initViewModel()

        initBinding()

        observeUiState()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, CounterViewModelFactory(Counter()))[CounterViewModel::class.java]
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeUiState() {
        viewModel.counterUiState.observe(this) { counterUiState ->
            counterUiState ?: return@observe

            showErrorMessage(counterUiState.errorMessage)
        }
    }

    private fun showErrorMessage(errorMessage: UiText?) {
        errorMessage ?: return

        Toast.makeText(this, errorMessage.asString(this), Toast.LENGTH_LONG).show()

        viewModel.shownErrorMessage()
    }
}
