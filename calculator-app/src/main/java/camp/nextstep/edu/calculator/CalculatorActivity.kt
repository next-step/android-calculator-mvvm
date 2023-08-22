package camp.nextstep.edu.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import camp.nextstep.edu.calculator.viewmodel.CalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.model = viewModel

        viewModel.uiState.observe(this) {
            showIncompleteExpressionError()
        }

        viewModel.expression.observe(this) {
            binding.recyclerView.visibility = View.GONE
        }

        val adapter = HistoryAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.histories.observe(this) {
            binding.recyclerView.visibility = View.VISIBLE
            adapter.submitList(it)
        }
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
