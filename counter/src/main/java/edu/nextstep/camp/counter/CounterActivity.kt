package edu.nextstep.camp.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding
import edu.nextstep.camp.domain.counter.Counter

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    private val viewModel: CounterViewModel by viewModels { CounterViewModelFactory(Counter()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCounterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initBinding()

        observeUiState()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeUiState() {
        viewModel.errorMessage.observe(this) { errorMessage ->
            errorMessage ?: return@observe

            showErrorMessage(errorMessage)
        }
    }

    private fun showErrorMessage(errorMessage: UiText) {
        Toast.makeText(this, errorMessage.asString(this), Toast.LENGTH_LONG).show()
    }
}
