package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.data.Injector
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels {
        CalculatorViewModelFactory(Injector.provideSampleRepository(this))
    }

    private val adapter = HistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerView.adapter = adapter

        viewModel.historyList.observe(this) {
            adapter.setItems(it)
        }

        viewModel.loadHistories()

        viewModel.calculateErrorEvent.observe(this) { event ->
            if (event.consume() is CalculatorViewModel.CalculateError.ExpressionError)
                showIncompleteExpressionError()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveHistories()
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
