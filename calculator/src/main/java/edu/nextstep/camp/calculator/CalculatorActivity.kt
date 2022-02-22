package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private val viewModel by viewModels<CalculatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = CalculatorMemoryAdapter()
        observeEvent()
    }

    private fun observeEvent() {
        viewModel.incompleteExpressionEvent.observe(this) { showIncompleteExpressionError() }
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
