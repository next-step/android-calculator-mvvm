package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.calculator.injector.Injector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CalculatorViewModel(
                    memoryRepository = Injector.provideMemoryRepository(this@CalculatorActivity)
                ) as T
            }
        }
    }
    private val adapter by lazy { CalculatorAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)
        initViewModel()
        initRecyclerView()
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            viewModel.memoryFlow.collect {
                adapter.replaceAll(it)
            }
        }
        viewModel.eventShowIncompleteExpressionError.observe(this) {
            Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
    }
}
