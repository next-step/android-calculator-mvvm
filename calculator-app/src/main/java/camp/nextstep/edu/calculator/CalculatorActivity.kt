package camp.nextstep.edu.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import camp.nextstep.edu.calculator.viewmodel.CalculatorViewModel
import camp.nextstep.edu.calculator.viewmodel.ViewModelFactory

class CalculatorActivity : AppCompatActivity() {
    private val viewModel: CalculatorViewModel by viewModels {
        ViewModelFactory((applicationContext as CalculatorApplication).historyRepository)
    }

    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.model = viewModel

        viewModel.uiState.observe(this) {
            showIncompleteExpressionError()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.expression.observe(this) {
            binding.recyclerView.visibility = View.GONE
        }

        viewModel.histories.observe(this) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.recyclerView.adapter = HistoryAdapter(it)
        }
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
