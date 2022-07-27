package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.CalculatorViewModel.ViewEvent
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCalculatorBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<CalculatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.event.observe(this) { event ->
            event.consume()?.let { handleViewEvent(it) }
        }
    }

    private fun handleViewEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            ViewEvent.IncompleteExpressionError ->
                Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
        }
    }
}
