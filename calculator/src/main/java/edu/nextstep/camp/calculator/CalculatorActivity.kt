package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.calculator.memory.MemoryAdapter

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel: CalculatorViewModel by viewModels { ViewModelFactory(this) }
    private val memoryAdapter: MemoryAdapter by lazy { MemoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator)
        setContentView(binding.root)

        setupViewModel()
        observeViewModel()

        setupUi()
    }

    private fun setupViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeViewModel() {
        with(viewModel) {
            showIncompleteExpressionError.observe(this@CalculatorActivity) {
                Toast.makeText(this@CalculatorActivity, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
            }

            memoriesResult.observe(this@CalculatorActivity) {
                memoryAdapter.submitList(it.items)
            }
        }
    }

    private fun setupUi() {
        setupMemoryMode()
    }

    private fun setupMemoryMode() {
        binding.recyclerView.adapter = memoryAdapter
    }
}
