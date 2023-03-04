package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import camp.nextstep.edu.calculator.di.ViewModelFactory
import kotlinx.coroutines.launch

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels { ViewModelFactory(this) }
    private lateinit var calculatorAdapter: CalculatorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setContentView(binding.root)
        setRecyclerViewAdapter()

        observeLiveData()
        setSavedRecords()
    }

    private fun setRecyclerViewAdapter() {
        calculatorAdapter = CalculatorAdapter()
        binding.recyclerView.adapter = calculatorAdapter
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    private fun observeLiveData() {
        viewModel.onCalculationErrorEvent.observe(this) {
            showIncompleteExpressionError()
        }
    }

    private fun setSavedRecords() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.savedRecords.collect {
                    calculatorAdapter.submitList(it)
                }
            }
        }
    }
}
