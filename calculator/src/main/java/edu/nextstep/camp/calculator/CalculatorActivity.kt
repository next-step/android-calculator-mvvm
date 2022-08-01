package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.calculator.memoryview.MemoryRecyclerViewAdapter

@AndroidEntryPoint
class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding
    private lateinit var memoryAdapter: MemoryRecyclerViewAdapter

    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        observe()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        memoryAdapter = MemoryRecyclerViewAdapter().also {
            binding.recyclerView.adapter = it
        }
    }

    private fun observe() {
        with(viewModel) {
            error.observe(this@CalculatorActivity) {
                when(it) {
                    is CalculatorErrorEvent.IncompleteExpressionError -> showIncompleteExpressionError()
                }
            }

            memoryLog.observe(this@CalculatorActivity) { calculatingLogs ->
                memoryAdapter.submitList(calculatingLogs)
            }
        }
    }


    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
