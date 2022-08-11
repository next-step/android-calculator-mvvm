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
    private val calculatorVM by viewModels<CalculatorViewModel> { CalculatorViewModelFactory(this) }
    private val calculatorHistoryAdapter = CalculatorHistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@CalculatorActivity
            viewModel = calculatorVM
        }
        setContentView(binding.root)
        initView()

        observeExpressionHistory()
        observeIncompleteExpressionErrorEvent()
    }

    private fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = calculatorHistoryAdapter
    }

    private fun observeExpressionHistory() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            calculatorVM.expressionHistory
                .collect { history ->
                    history?.let { calculatorHistoryAdapter.submitList(it) }
                }
        }
    }

    private fun observeIncompleteExpressionErrorEvent() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            calculatorVM.errorEvent
                .collect {
                    when (it.cause) {
                        is IncompleteExpressionException -> showToast(resources.getString(R.string.incomplete_expression))
                    }
                }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
