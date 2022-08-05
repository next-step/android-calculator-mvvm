package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.calculator.domain.IncompleteExpressionException
import kotlinx.coroutines.launch

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val calculatorVM by viewModels<CalculatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@CalculatorActivity
            viewModel = calculatorVM
        }
        setContentView(binding.root)

        observeIncompleteExpressionErrorEvent()
    }

    private fun observeIncompleteExpressionErrorEvent() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            calculatorVM.errorEvent
                .collect {
                    when (it.cause) {
                        is IncompleteExpressionException -> showIncompleteExpressionToast()
                    }
                }
        }
    }

    private fun showIncompleteExpressionToast() {
        Toast.makeText(this@CalculatorActivity, R.string.incomplete_expression, Toast.LENGTH_SHORT)
            .show()
    }
}
