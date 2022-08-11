package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculatorBinding.inflate(layoutInflater)

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
